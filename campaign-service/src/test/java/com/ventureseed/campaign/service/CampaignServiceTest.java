package com.ventureseed.campaign.service;

import com.ventureseed.campaign.dto.CampaignResponse;
import com.ventureseed.campaign.dto.ContributeInternalRequest;
import com.ventureseed.campaign.dto.CreateCampaignRequest;
import com.ventureseed.campaign.model.Campaign;
import com.ventureseed.campaign.model.CampaignCategory;
import com.ventureseed.campaign.model.CampaignStatus;
import com.ventureseed.campaign.repository.CampaignRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CampaignServiceTest {

    @Mock
    private CampaignRepository campaignRepository;

    @InjectMocks
    private CampaignService campaignService;

    private Campaign testCampaign;

    @BeforeEach
    void setUp() {
        testCampaign = new Campaign(
                "AI Medical Diagnostics",
                "Next-gen edge diagnostics",
                "Full description here",
                CampaignCategory.BIOTECH,
                BigDecimal.valueOf(50000.00),
                1L,
                "Elena Rostova",
                "creator@ventureseed.com",
                LocalDate.now().plusDays(30),
                "https://example.com/img.jpg"
        );
        testCampaign.setId(10L);
    }

    @Test
    void testCreateCampaign_Success() {
        CreateCampaignRequest request = new CreateCampaignRequest();
        request.setTitle("AI Medical Diagnostics");
        request.setTagline("Next-gen edge diagnostics");
        request.setDescription("Full description here");
        request.setCategory(CampaignCategory.BIOTECH);
        request.setGoalAmount(BigDecimal.valueOf(50000.00));
        request.setCreatorId(1L);
        request.setCreatorName("Elena Rostova");
        request.setCreatorEmail("creator@ventureseed.com");
        request.setDeadline(LocalDate.now().plusDays(30));
        request.setImageUrl("https://example.com/img.jpg");

        when(campaignRepository.save(any(Campaign.class))).thenReturn(testCampaign);

        CampaignResponse response = campaignService.createCampaign(request);

        assertNotNull(response);
        assertEquals("AI Medical Diagnostics", response.getTitle());
        assertEquals(BigDecimal.valueOf(50000.00), response.getGoalAmount());
        assertEquals(CampaignStatus.ACTIVE, response.getStatus());
        verify(campaignRepository, times(1)).save(any(Campaign.class));
    }

    @Test
    void testProcessContributionInternal_SuccessAndFunded() {
        testCampaign.setCurrentAmount(BigDecimal.valueOf(49500.00));
        testCampaign.setGoalAmount(BigDecimal.valueOf(50000.00));
        testCampaign.setBackerCount(10);

        when(campaignRepository.findById(10L)).thenReturn(Optional.of(testCampaign));
        when(campaignRepository.save(any(Campaign.class))).thenReturn(testCampaign);

        ContributeInternalRequest request = new ContributeInternalRequest(BigDecimal.valueOf(1000.00), 2L);
        CampaignResponse response = campaignService.processContributionInternal(10L, request);

        assertNotNull(response);
        assertEquals(BigDecimal.valueOf(50500.00), testCampaign.getCurrentAmount());
        assertEquals(11, testCampaign.getBackerCount());
        assertEquals(CampaignStatus.FUNDED, testCampaign.getStatus());
    }

    @Test
    void testProcessContributionInternal_InactiveThrows() {
        testCampaign.setStatus(CampaignStatus.CANCELLED);
        when(campaignRepository.findById(10L)).thenReturn(Optional.of(testCampaign));

        ContributeInternalRequest request = new ContributeInternalRequest(BigDecimal.valueOf(100.00), 2L);
        assertThrows(IllegalStateException.class, () -> campaignService.processContributionInternal(10L, request));
    }
}
