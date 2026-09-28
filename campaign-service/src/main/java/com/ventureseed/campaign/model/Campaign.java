package com.ventureseed.campaign.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "campaigns")
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 500)
    private String tagline;

    @Lob
    @Column(nullable = false, length = 4000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CampaignCategory category;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal goalAmount;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal currentAmount = BigDecimal.ZERO;

    @Column(nullable = false)
    private Integer backerCount = 0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CampaignStatus status = CampaignStatus.ACTIVE;

    @Column(nullable = false)
    private Long creatorId;

    @Column(nullable = false)
    private String creatorName;

    @Column(nullable = false)
    private String creatorEmail;

    @Column(nullable = false)
    private LocalDate deadline;

    @Column(length = 500)
    private String imageUrl;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public Campaign() {
    }

    public Campaign(String title, String tagline, String description, CampaignCategory category,
                    BigDecimal goalAmount, Long creatorId, String creatorName, String creatorEmail,
                    LocalDate deadline, String imageUrl) {
        this.title = title;
        this.tagline = tagline;
        this.description = description;
        this.category = category;
        this.goalAmount = goalAmount;
        this.currentAmount = BigDecimal.ZERO;
        this.backerCount = 0;
        this.status = CampaignStatus.ACTIVE;
        this.creatorId = creatorId;
        this.creatorName = creatorName;
        this.creatorEmail = creatorEmail;
        this.deadline = deadline;
        this.imageUrl = imageUrl;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
        if (this.updatedAt == null) {
            this.updatedAt = LocalDateTime.now();
        }
        if (this.currentAmount == null) {
            this.currentAmount = BigDecimal.ZERO;
        }
        if (this.backerCount == null) {
            this.backerCount = 0;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public double getFundingPercentage() {
        if (goalAmount == null || goalAmount.compareTo(BigDecimal.ZERO) <= 0) {
            return 0.0;
        }
        BigDecimal pct = currentAmount.divide(goalAmount, 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));
        return pct.setScale(2, RoundingMode.HALF_UP).doubleValue();
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
