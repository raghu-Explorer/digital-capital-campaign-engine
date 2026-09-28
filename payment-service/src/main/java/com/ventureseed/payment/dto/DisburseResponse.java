package com.ventureseed.payment.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DisburseResponse {

    private String disbursementReference;
    private Long campaignId;
    private Long creatorId;
    private BigDecimal amount;
    private String status;
    private String destinationAccount;
    private LocalDateTime disbursedAt;

    public DisburseResponse() {
    }

    public DisburseResponse(String disbursementReference, Long campaignId, Long creatorId,
                            BigDecimal amount, String status, String destinationAccount,
                            LocalDateTime disbursedAt) {
        this.disbursementReference = disbursementReference;
        this.campaignId = campaignId;
        this.creatorId = creatorId;
        this.amount = amount;
        this.status = status;
        this.destinationAccount = destinationAccount;
        this.disbursedAt = disbursedAt;
    }

    public String getDisbursementReference() {
        return disbursementReference;
    }

    public void setDisbursementReference(String disbursementReference) {
        this.disbursementReference = disbursementReference;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(String destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public LocalDateTime getDisbursedAt() {
        return disbursedAt;
    }

    public void setDisbursedAt(LocalDateTime disbursedAt) {
        this.disbursedAt = disbursedAt;
    }
}
