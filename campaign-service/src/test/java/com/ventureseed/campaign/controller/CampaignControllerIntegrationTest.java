package com.ventureseed.campaign.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ventureseed.campaign.dto.CreateCampaignRequest;
import com.ventureseed.campaign.model.CampaignCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CampaignControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllCampaigns() throws Exception {
        mockMvc.perform(get("/api/campaigns"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testGetMetrics() throws Exception {
        mockMvc.perform(get("/api/campaigns/metrics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalRaised").exists())
                .andExpect(jsonPath("$.totalCampaigns").exists());
    }

    @Test
    void testCreateCampaign() throws Exception {
        CreateCampaignRequest request = new CreateCampaignRequest();
        request.setTitle("Solar Stirling Engine Generator");
        request.setTagline("High efficiency micro concentrated solar thermal power");
        request.setDescription("A closed-cycle Stirling engine combined with parabolic solar collector.");
        request.setCategory(CampaignCategory.CLEAN_ENERGY);
        request.setGoalAmount(BigDecimal.valueOf(80000.00));
        request.setCreatorId(1L);
        request.setCreatorName("Elena Rostova");
        request.setCreatorEmail("creator@ventureseed.com");
        request.setDeadline(LocalDate.now().plusDays(40));

        mockMvc.perform(post("/api/campaigns")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.title").value("Solar Stirling Engine Generator"))
                .andExpect(jsonPath("$.status").value("ACTIVE"));
    }
}
