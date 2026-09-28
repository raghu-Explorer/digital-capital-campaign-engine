package com.ventureseed.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ventureseed.auth.dto.LoginRequest;
import com.ventureseed.auth.dto.RegisterRequest;
import com.ventureseed.auth.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testRegisterAndLoginIntegration() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest(
                "Jane Innovator",
                "jane.innovator@example.com",
                "securePass123",
                Role.ROLE_CREATOR
        );

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andExpect(jsonPath("$.email").value("jane.innovator@example.com"))
                .andExpect(jsonPath("$.role").value("ROLE_CREATOR"));

        LoginRequest loginRequest = new LoginRequest("jane.innovator@example.com", "securePass123");

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andExpect(jsonPath("$.email").value("jane.innovator@example.com"));
    }
}
