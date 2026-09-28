package com.ventureseed.contribution.service;

import com.ventureseed.contribution.client.CampaignClient;
import com.ventureseed.contribution.client.PaymentClient;
import com.ventureseed.contribution.dto.*;
import com.ventureseed.contribution.model.Contribution;
import com.ventureseed.contribution.model.ContributionStatus;
import com.ventureseed.contribution.repository.ContributionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContributionServiceTest {

    @Mock
    private ContributionRepository contributionRepository;

    @Mock
    private CampaignClient campaignClient;

    @Mock
    private PaymentClient paymentClient;

    @InjectMocks
    private ContributionService contributionService;

    private CreateContributionRequest testRequest;

    @BeforeEach
    void setUp() {
        testRequest = new CreateContributionRequest();
        testRequest.setCampaignId(1L);
        testRequest.setBackerId(2L);
        testRequest.setBackerName("David Vance");
        testRequest.setBackerEmail("backer@ventureseed.com");
        testRequest.setAmount(BigDecimal.valueOf(500.00));
        testRequest.setPaymentMethod("CREDIT_CARD");
        testRequest.setMessage("Go VentureSeed!");
        testRequest.setIdempotencyKey("idemp-contrib-123");
    }

    @Test
    void testContribute_FullOrchestrationSuccess() {
        // Step 1: Validate Campaign
        Map<String, Object> validationResponse = new HashMap<>();
        validationResponse.put("campaignId", 1L);
        validationResponse.put("active", true);
        when(campaignClient.validateActive(1L)).thenReturn(validationResponse);

        // Step 2: Payment Charge
        PaymentResultDto paymentResult = new PaymentResultDto();
        paymentResult.setTransactionReference("txn_test_789456");
        paymentResult.setStatus("SUCCESS");
        when(paymentClient.chargePayment(any(PaymentChargeRequest.class))).thenReturn(paymentResult);

        // Step 3: Campaign Update
        CampaignDto campaignDto = new CampaignDto();
        campaignDto.setId(1L);
        when(campaignClient.updateCampaignFunding(eq(1L), any(ContributeInternalDto.class))).thenReturn(campaignDto);

        // Step 4: Save Contribution
        Contribution savedContribution = new Contribution(
                1L, 2L, "David Vance", "backer@ventureseed.com",
                BigDecimal.valueOf(500.00), "CREDIT_CARD", ContributionStatus.COMPLETED,
                "txn_test_789456", "Go VentureSeed!"
        );
        savedContribution.setId(99L);
        when(contributionRepository.save(any(Contribution.class))).thenReturn(savedContribution);

        ContributionResponse response = contributionService.contribute(testRequest);

        assertNotNull(response);
        assertEquals(99L, response.getId());
        assertEquals("txn_test_789456", response.getTransactionReference());
        assertEquals(ContributionStatus.COMPLETED, response.getStatus());

        // Verify the entire pipeline was triggered in order
        verify(campaignClient, times(1)).validateActive(1L);
        verify(paymentClient, times(1)).chargePayment(any(PaymentChargeRequest.class));
        verify(campaignClient, times(1)).updateCampaignFunding(eq(1L), any(ContributeInternalDto.class));
        verify(contributionRepository, times(1)).save(any(Contribution.class));
    }

    @Test
    void testContribute_InactiveCampaignFailsEarly() {
        Map<String, Object> validationResponse = new HashMap<>();
        validationResponse.put("campaignId", 1L);
        validationResponse.put("active", false);
        when(campaignClient.validateActive(1L)).thenReturn(validationResponse);

        assertThrows(IllegalStateException.class, () -> contributionService.contribute(testRequest));

        verify(paymentClient, never()).chargePayment(any());
        verify(campaignClient, never()).updateCampaignFunding(any(), any());
        verify(contributionRepository, never()).save(any());
    }
}
