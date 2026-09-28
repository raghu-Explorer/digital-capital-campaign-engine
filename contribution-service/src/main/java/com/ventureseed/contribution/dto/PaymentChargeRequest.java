package com.ventureseed.contribution.dto;

import java.math.BigDecimal;

public class PaymentChargeRequest {
    private Long campaignId;
    private Long backerId;
    private BigDecimal amount;
    private String currency = "USD";
    private String paymentMethod;
    private String idempotencyKey;

    public PaymentChargeRequest() {
    }

    public PaymentChargeRequest(Long campaignId, Long backerId, BigDecimal amount, String paymentMethod, String idempotencyKey) {
        this.campaignId = campaignId;
        this.backerId = backerId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.idempotencyKey = idempotencyKey;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setIdempotencyKey(String idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }
}
