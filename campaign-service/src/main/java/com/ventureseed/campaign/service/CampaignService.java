package com.ventureseed.campaign.service;

import com.ventureseed.campaign.dto.*;
import com.ventureseed.campaign.model.Campaign;
import com.ventureseed.campaign.model.CampaignCategory;
import com.ventureseed.campaign.model.CampaignStatus;
import com.ventureseed.campaign.repository.CampaignRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;

    public CampaignService(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Transactional
    public CampaignResponse createCampaign(CreateCampaignRequest request) {
        Campaign campaign = new Campaign(
                request.getTitle(),
                request.getTagline(),
                request.getDescription(),
                request.getCategory(),
                request.getGoalAmount(),
                request.getCreatorId(),
                request.getCreatorName(),
                request.getCreatorEmail(),
                request.getDeadline(),
                request.getImageUrl()
        );

        Campaign saved = campaignRepository.save(campaign);
        return CampaignResponse.fromEntity(saved);
    }

    public List<CampaignResponse> getAllCampaigns(CampaignStatus status, CampaignCategory category, String search) {
        List<Campaign> campaigns;

        if (search != null && !search.trim().isEmpty()) {
            campaigns = campaignRepository.findByTitleContainingIgnoreCaseOrTaglineContainingIgnoreCase(search, search);
        } else if (status != null) {
            campaigns = campaignRepository.findByStatus(status);
        } else if (category != null) {
            campaigns = campaignRepository.findByCategory(category);
        } else {
            campaigns = campaignRepository.findAll();
        }

        return campaigns.stream()
                .map(CampaignResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public CampaignResponse getCampaignById(Long id) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Campaign not found with ID: " + id));
        return CampaignResponse.fromEntity(campaign);
    }

    @Transactional
    public CampaignResponse updateCampaign(Long id, UpdateCampaignRequest request) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Campaign not found with ID: " + id));

        if (request.getTitle() != null) campaign.setTitle(request.getTitle());
        if (request.getTagline() != null) campaign.setTagline(request.getTagline());
        if (request.getDescription() != null) campaign.setDescription(request.getDescription());
        if (request.getCategory() != null) campaign.setCategory(request.getCategory());
        if (request.getGoalAmount() != null) campaign.setGoalAmount(request.getGoalAmount());
        if (request.getDeadline() != null) campaign.setDeadline(request.getDeadline());
        if (request.getImageUrl() != null) campaign.setImageUrl(request.getImageUrl());
        if (request.getStatus() != null) campaign.setStatus(request.getStatus());

        Campaign updated = campaignRepository.save(campaign);
        return CampaignResponse.fromEntity(updated);
    }

    @Transactional
    public CampaignResponse processContributionInternal(Long campaignId, ContributeInternalRequest request) {
        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new IllegalArgumentException("Campaign not found with ID: " + campaignId));

        if (campaign.getStatus() != CampaignStatus.ACTIVE && campaign.getStatus() != CampaignStatus.FUNDED) {
            throw new IllegalStateException("Campaign is not accepting contributions. Current status: " + campaign.getStatus());
        }

        if (campaign.getDeadline().isBefore(LocalDate.now())) {
            campaign.setStatus(CampaignStatus.EXPIRED);
            campaignRepository.save(campaign);
            throw new IllegalStateException("Campaign deadline has passed on " + campaign.getDeadline());
        }

        campaign.setCurrentAmount(campaign.getCurrentAmount().add(request.getAmount()));
        campaign.setBackerCount(campaign.getBackerCount() + 1);

        if (campaign.getCurrentAmount().compareTo(campaign.getGoalAmount()) >= 0) {
            campaign.setStatus(CampaignStatus.FUNDED);
        }

        Campaign saved = campaignRepository.save(campaign);
        return CampaignResponse.fromEntity(saved);
    }

    public boolean validateCampaignActive(Long campaignId) {
        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new IllegalArgumentException("Campaign not found with ID: " + campaignId));

        if (campaign.getDeadline().isBefore(LocalDate.now())) {
            campaign.setStatus(CampaignStatus.EXPIRED);
            campaignRepository.save(campaign);
            return false;
        }

        return campaign.getStatus() == CampaignStatus.ACTIVE || campaign.getStatus() == CampaignStatus.FUNDED;
    }

    public PlatformMetricsResponse getPlatformMetrics() {
        BigDecimal totalRaised = campaignRepository.getTotalFundsRaised();
        Long totalBackers = campaignRepository.getTotalBackersCount();
        long totalCampaigns = campaignRepository.count();
        long activeCount = campaignRepository.countByStatus(CampaignStatus.ACTIVE);
        long fundedCount = campaignRepository.countByStatus(CampaignStatus.FUNDED);

        return new PlatformMetricsResponse(
                totalRaised != null ? totalRaised : BigDecimal.ZERO,
                totalCampaigns,
                activeCount,
                fundedCount,
                totalBackers != null ? totalBackers : 0L
        );
    }
}
