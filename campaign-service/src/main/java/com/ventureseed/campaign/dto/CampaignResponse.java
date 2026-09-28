package com.ventureseed.campaign.dto;

import com.ventureseed.campaign.model.Campaign;
import com.ventureseed.campaign.model.CampaignCategory;
import com.ventureseed.campaign.model.CampaignStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CampaignResponse {

    private Long id;
    private String title;
    private String tagline;
    private String description;
    private CampaignCategory category;
    private BigDecimal goalAmount;
    private BigDecimal currentAmount;
    private Integer backerCount;
    private CampaignStatus status;
    private Long creatorId;
    private String creatorName;
    private String creatorEmail;
    private LocalDate deadline;
    private String imageUrl;
    private double fundingPercentage;
    private long daysRemaining;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CampaignResponse() {
    }

    public static CampaignResponse fromEntity(Campaign campaign) {
        CampaignResponse resp = new CampaignResponse();
        resp.setId(campaign.getId());
        resp.setTitle(campaign.getTitle());
        resp.setTagline(campaign.getTagline());
        resp.setDescription(campaign.getDescription());
        resp.setCategory(campaign.getCategory());
        resp.setGoalAmount(campaign.getGoalAmount());
        resp.setCurrentAmount(campaign.getCurrentAmount());
        resp.setBackerCount(campaign.getBackerCount());
        resp.setStatus(campaign.getStatus());
        resp.setCreatorId(campaign.getCreatorId());
        resp.setCreatorName(campaign.getCreatorName());
        resp.setCreatorEmail(campaign.getCreatorEmail());
        resp.setDeadline(campaign.getDeadline());
        resp.setImageUrl(campaign.getImageUrl());
        resp.setFundingPercentage(campaign.getFundingPercentage());
        resp.setCreatedAt(campaign.getCreatedAt());
        resp.setUpdatedAt(campaign.getUpdatedAt());

        long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), campaign.getDeadline());
        resp.setDaysRemaining(Math.max(0, daysLeft));

        return resp;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CampaignCategory getCategory() {
        return category;
    }

    public void setCategory(CampaignCategory category) {
        this.category = category;
    }

    public BigDecimal getGoalAmount() {
        return goalAmount;
    }

    public void setGoalAmount(BigDecimal goalAmount) {
        this.goalAmount = goalAmount;
    }

    public BigDecimal getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(BigDecimal currentAmount) {
        this.currentAmount = currentAmount;
    }

    public Integer getBackerCount() {
        return backerCount;
    }

    public void setBackerCount(Integer backerCount) {
        this.backerCount = backerCount;
    }

    public CampaignStatus getStatus() {
        return status;
    }

    public void setStatus(CampaignStatus status) {
        this.status = status;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getCreatorEmail() {
        return creatorEmail;
    }

    public void setCreatorEmail(String creatorEmail) {
        this.creatorEmail = creatorEmail;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getFundingPercentage() {
        return fundingPercentage;
    }

    public void setFundingPercentage(double fundingPercentage) {
        this.fundingPercentage = fundingPercentage;
    }

    public long getDaysRemaining() {
        return daysRemaining;
    }

    public void setDaysRemaining(long daysRemaining) {
        this.daysRemaining = daysRemaining;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
