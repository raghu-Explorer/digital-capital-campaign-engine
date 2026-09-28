package com.ventureseed.auth.service;

import com.ventureseed.auth.dto.AuthResponse;
import com.ventureseed.auth.dto.LoginRequest;
import com.ventureseed.auth.dto.RegisterRequest;
import com.ventureseed.auth.model.Role;
import com.ventureseed.auth.model.User;
import com.ventureseed.auth.repository.UserRepository;
import com.ventureseed.auth.security.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private AuthService authService;

    private User sampleUser;

    @BeforeEach
    void setUp() {
        sampleUser = new User("Test Creator", "test@ventureseed.com", "encodedPassword", Role.ROLE_CREATOR);
        sampleUser.setId(1L);
    }

    @Test
    void testRegister_Success() {
        RegisterRequest request = new RegisterRequest("Test Creator", "test@ventureseed.com", "password123", Role.ROLE_CREATOR);

        when(userRepository.existsByEmail("test@ventureseed.com")).thenReturn(false);
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(sampleUser);
        when(jwtTokenProvider.generateToken(any(User.class))).thenReturn("mock-jwt-token");

        AuthResponse response = authService.register(request);

        assertNotNull(response);
        assertEquals("mock-jwt-token", response.getToken());
        assertEquals("test@ventureseed.com", response.getEmail());
        assertEquals(Role.ROLE_CREATOR, response.getRole());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testRegister_EmailAlreadyExists() {
        RegisterRequest request = new RegisterRequest("Test Creator", "test@ventureseed.com", "password123", Role.ROLE_CREATOR);

        when(userRepository.existsByEmail("test@ventureseed.com")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> authService.register(request));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testLogin_Success() {
        LoginRequest request = new LoginRequest("test@ventureseed.com", "password123");

        when(userRepository.findByEmail("test@ventureseed.com")).thenReturn(Optional.of(sampleUser));
        when(passwordEncoder.matches("password123", "encodedPassword")).thenReturn(true);
        when(jwtTokenProvider.generateToken(sampleUser)).thenReturn("mock-jwt-token");

        AuthResponse response = authService.login(request);

        assertNotNull(response);
        assertEquals("mock-jwt-token", response.getToken());
        assertEquals("Test Creator", response.getFullName());
    }

    @Test
    void testLogin_InvalidPassword() {
        LoginRequest request = new LoginRequest("test@ventureseed.com", "wrongpassword");

        when(userRepository.findByEmail("test@ventureseed.com")).thenReturn(Optional.of(sampleUser));
        when(passwordEncoder.matches("wrongpassword", "encodedPassword")).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> authService.login(request));
    }
}
