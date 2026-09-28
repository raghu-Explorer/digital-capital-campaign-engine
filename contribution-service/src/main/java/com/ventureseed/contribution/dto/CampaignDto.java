package com.ventureseed.contribution.dto;

import java.math.BigDecimal;
import java.util.Map;

public class CampaignDto {
    private Long id;
    private String title;
    private String status;
    private BigDecimal goalAmount;
    private BigDecimal currentAmount;
    private Integer backerCount;

    public CampaignDto() {
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
