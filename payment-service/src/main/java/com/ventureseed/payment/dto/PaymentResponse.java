package com.ventureseed.payment.dto;

import com.ventureseed.payment.model.PaymentMethod;
import com.ventureseed.payment.model.PaymentStatus;
import com.ventureseed.payment.model.PaymentTransaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentResponse {

    private Long id;
    private String transactionReference;
    private String idempotencyKey;
    private Long backerId;
    private Long campaignId;
    private BigDecimal amount;
    private String currency;
    private PaymentMethod paymentMethod;
    private PaymentStatus status;
    private String failureReason;
    private LocalDateTime createdAt;
    private LocalDateTime settledAt;

    public PaymentResponse() {
    }

    public static PaymentResponse fromEntity(PaymentTransaction txn) {
        PaymentResponse resp = new PaymentResponse();
        resp.setId(txn.getId());
        resp.setTransactionReference(txn.getTransactionReference());
        resp.setIdempotencyKey(txn.getIdempotencyKey());
        resp.setBackerId(txn.getBackerId());
        resp.setCampaignId(txn.getCampaignId());
        resp.setAmount(txn.getAmount());
        resp.setCurrency(txn.getCurrency());
        resp.setPaymentMethod(txn.getPaymentMethod());
        resp.setStatus(txn.getStatus());
        resp.setFailureReason(txn.getFailureReason());
        resp.setCreatedAt(txn.getCreatedAt());
        resp.setSettledAt(txn.getSettledAt());
        return resp;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setIdempotencyKey(String idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }

    public Long getBackerId() {
        return backerId;
    }

    public void setBackerId(Long backerId) {
        this.backerId = backerId;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
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

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getSettledAt() {
        return settledAt;
    }

    public void setSettledAt(LocalDateTime settledAt) {
        this.settledAt = settledAt;
    }
}
