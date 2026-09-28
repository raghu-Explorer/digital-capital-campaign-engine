package com.ventureseed.gateway.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    @Value("${jwt.secret}")

    private static final List<String> OPEN_API_ENDPOINTS = List.of(
            "/api/auth/login",
            "/api/auth/register",
            "/api/auth/validate",
            "/actuator",
            "/h2-console"
    );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        HttpMethod method = request.getMethod();

        // Allow CORS pre-flight OPTIONS requests
        if (HttpMethod.OPTIONS.equals(method)) {
            return chain.filter(exchange);
        }

        // Allow open public endpoints
        if (isPublicEndpoint(path, method)) {
            return chain.filter(exchange);
        }

        // Secured endpoint check: require Authorization header
        if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            return onError(exchange, "Authorization header is missing", HttpStatus.UNAUTHORIZED);
        }

        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return onError(exchange, "Invalid Authorization token format. Must be 'Bearer <token>'", HttpStatus.UNAUTHORIZED);
        }

        String token = authHeader.substring(7);

        try {
            SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
            Claims claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            // Decorate downstream request with extracted JWT identity headers
            ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                    .header("X-User-Id", String.valueOf(claims.get("userId")))
                    .header("X-User-Email", claims.getSubject())
                    .header("X-User-Role", String.valueOf(claims.get("role")))
                    .build();

            return chain.filter(exchange.mutate().request(mutatedRequest).build());

        } catch (JwtException | IllegalArgumentException e) {
            return onError(exchange, "JWT validation failed: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    private boolean isPublicEndpoint(String path, HttpMethod method) {
        // Read-only GET requests to campaigns are public for browsing
        if (HttpMethod.GET.equals(method) && path.startsWith("/api/campaigns")) {
            return true;
        }

        for (String openEndpoint : OPEN_API_ENDPOINTS) {
            if (path.startsWith(openEndpoint)) {
                return true;
            }
        }
        return false;
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        String jsonError = String.format("{\"error\": \"%s\", \"status\": %d}", err, httpStatus.value());
        byte[] bytes = jsonError.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bytes);

        return response.writeWith(Mono.just(buffer));
    }

    @Override
    public int getOrder() {
        return -1; // Execute before routing filters
    }
}
