package com.ventureseed.payment.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class DisburseRequest {

    @NotNull(message = "Campaign ID is required")
    private Long campaignId;

    @NotNull(message = "Creator ID is required")
    private Long creatorId;

    @NotNull(message = "Disbursement amount is required")
    @DecimalMin(value = "1.00", message = "Amount must be at least 1.00")
    private BigDecimal amount;

    @NotBlank(message = "Payout account number or wallet is required")
    private String destinationAccount;

    public DisburseRequest() {
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

    public String getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(String destinationAccount) {
        this.destinationAccount = destinationAccount;
    }
}
