package com.ventureseed.contribution.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ContributionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllContributions() throws Exception {
        mockMvc.perform(get("/api/contributions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testGetStatsTotal() throws Exception {
        mockMvc.perform(get("/api/contributions/stats/total"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalContributions").exists());
    }
}
