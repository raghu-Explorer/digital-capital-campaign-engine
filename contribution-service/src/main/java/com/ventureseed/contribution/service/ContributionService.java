package com.ventureseed.contribution.service;

import com.ventureseed.contribution.client.CampaignClient;
import com.ventureseed.contribution.client.PaymentClient;
import com.ventureseed.contribution.dto.*;
import com.ventureseed.contribution.model.Contribution;
import com.ventureseed.contribution.model.ContributionStatus;
import com.ventureseed.contribution.repository.ContributionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ContributionService {

    private final ContributionRepository contributionRepository;
    private final CampaignClient campaignClient;
    private final PaymentClient paymentClient;

    public ContributionService(ContributionRepository contributionRepository,
                               CampaignClient campaignClient,
                               PaymentClient paymentClient) {
        this.contributionRepository = contributionRepository;
        this.campaignClient = campaignClient;
        this.paymentClient = paymentClient;
    }

    /**
     * Inter-Service Communication Flow:
     * 1. Contribution -> Campaign: Validate campaign is ACTIVE and not expired
     * 2. Contribution -> Payment: Orchestrate charge transaction with idempotency key
     * 3. Contribution -> Campaign: Update funded metrics and backer count
     * 4. Record contribution in local ledger
     */
    @Transactional
    public ContributionResponse contribute(CreateContributionRequest request) {
        // Step 1: Inter-service check: Contribution -> Campaign
        Map<String, Object> campaignValidation;
        try {
            campaignValidation = campaignClient.validateActive(request.getCampaignId());
        } catch (Exception e) {
            throw new IllegalStateException("Failed to communicate with Campaign Service: " + e.getMessage());
        }

        if (campaignValidation == null || !Boolean.TRUE.equals(campaignValidation.get("active"))) {
            throw new IllegalStateException("Campaign #" + request.getCampaignId() + " is not active or accepting contributions.");
        }

        // Step 2: Inter-service charge: Contribution -> Payment
        String idempotencyKey = request.getIdempotencyKey();
        if (idempotencyKey == null || idempotencyKey.trim().isEmpty()) {
            idempotencyKey = "contrib_" + UUID.randomUUID().toString();
        }

        PaymentChargeRequest chargeRequest = new PaymentChargeRequest(
                request.getCampaignId(),
                request.getBackerId(),
                request.getAmount(),
                request.getPaymentMethod(),
                idempotencyKey
        );

        PaymentResultDto paymentResult;
        try {
            paymentResult = paymentClient.chargePayment(chargeRequest);
        } catch (Exception e) {
            throw new IllegalStateException("Payment processing failed: " + e.getMessage());
        }

        if (paymentResult == null || !"SUCCESS".equalsIgnoreCase(paymentResult.getStatus())) {
            throw new IllegalStateException("Payment charge was declined by processor");
        }

        // Step 3: Inter-service update: Contribution -> Campaign
        try {
            ContributeInternalDto internalDto = new ContributeInternalDto(request.getAmount(), request.getBackerId());
            campaignClient.updateCampaignFunding(request.getCampaignId(), internalDto);
        } catch (Exception e) {
            // Note: In enterprise microservices, this would trigger compensation or saga rollback
            throw new IllegalStateException("Failed to update campaign funding metrics: " + e.getMessage());
        }

        // Step 4: Persist Contribution in ledger
        Contribution contribution = new Contribution(
                request.getCampaignId(),
                request.getBackerId(),
                request.getBackerName(),
                request.getBackerEmail(),
                request.getAmount(),
                request.getPaymentMethod(),
                ContributionStatus.COMPLETED,
                paymentResult.getTransactionReference(),
                request.getMessage()
        );

        Contribution saved = contributionRepository.save(contribution);
        return ContributionResponse.fromEntity(saved);
    }

    public List<ContributionResponse> getAllContributions() {
        return contributionRepository.findAll().stream()
                .map(ContributionResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public List<ContributionResponse> getContributionsByCampaign(Long campaignId) {
        return contributionRepository.findByCampaignIdOrderByCreatedAtDesc(campaignId).stream()
                .map(ContributionResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public List<ContributionResponse> getContributionsByBacker(Long backerId) {
        return contributionRepository.findByBackerIdOrderByCreatedAtDesc(backerId).stream()
                .map(ContributionResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public ContributionResponse getContributionById(Long id) {
        Contribution contribution = contributionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contribution not found with ID: " + id));
        return ContributionResponse.fromEntity(contribution);
    }

    public BigDecimal getTotalContributions() {
        return contributionRepository.getTotalCompletedContributions();
    }
}
