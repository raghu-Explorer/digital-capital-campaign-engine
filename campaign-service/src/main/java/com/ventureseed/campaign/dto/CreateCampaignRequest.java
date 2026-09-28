package com.ventureseed.campaign.dto;

import com.ventureseed.campaign.model.CampaignCategory;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateCampaignRequest {

    @NotBlank(message = "Title is required")
    @Size(max = 200, message = "Title cannot exceed 200 characters")
    private String title;

    @Size(max = 500, message = "Tagline cannot exceed 500 characters")
    private String tagline;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Category is required")
    private CampaignCategory category;

    @NotNull(message = "Goal amount is required")
    @DecimalMin(value = "100.00", message = "Goal amount must be at least 100.00")
    private BigDecimal goalAmount;

    @NotNull(message = "Creator ID is required")
    private Long creatorId;

    @NotBlank(message = "Creator name is required")
    private String creatorName;

    @NotBlank(message = "Creator email is required")
    private String creatorEmail;

    @NotNull(message = "Deadline is required")
    @Future(message = "Deadline must be in the future")
    private LocalDate deadline;

    private String imageUrl;

    public CreateCampaignRequest() {
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
}
