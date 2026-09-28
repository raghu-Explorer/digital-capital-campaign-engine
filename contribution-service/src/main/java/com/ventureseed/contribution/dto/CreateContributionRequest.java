package com.ventureseed.contribution.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CreateContributionRequest {

    @NotNull(message = "Campaign ID is required")
    private Long campaignId;

    @NotNull(message = "Backer ID is required")
    private Long backerId;

    @NotBlank(message = "Backer name is required")
    private String backerName;

    @NotBlank(message = "Backer email is required")
    private String backerEmail;

    @NotNull(message = "Contribution amount is required")
    @DecimalMin(value = "1.00", message = "Contribution amount must be at least 1.00")
    private BigDecimal amount;

    @NotBlank(message = "Payment method is required")
    private String paymentMethod;

    private String message;

    private String idempotencyKey;

    public CreateContributionRequest() {
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public Long getBackerId() {
        return backerId;
    }

    public void setBackerId(Long backerId) {
        this.backerId = backerId;
    }

    public String getBackerName() {
        return backerName;
    }

    public void setBackerName(String backerName) {
        this.backerName = backerName;
    }

    public String getBackerEmail() {
        return backerEmail;
    }

    public void setBackerEmail(String backerEmail) {
        this.backerEmail = backerEmail;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setIdempotencyKey(String idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }
}
