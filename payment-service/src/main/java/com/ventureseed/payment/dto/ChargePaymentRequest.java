package com.ventureseed.payment.dto;

import com.ventureseed.payment.model.PaymentMethod;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ChargePaymentRequest {

    @NotNull(message = "Campaign ID is required")
    private Long campaignId;

    @NotNull(message = "Backer ID is required")
    private Long backerId;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "1.00", message = "Amount must be at least 1.00")
    private BigDecimal amount;

    private String currency = "USD";

    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod;

    private String idempotencyKey;

    public ChargePaymentRequest() {
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

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setIdempotencyKey(String idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }
}
