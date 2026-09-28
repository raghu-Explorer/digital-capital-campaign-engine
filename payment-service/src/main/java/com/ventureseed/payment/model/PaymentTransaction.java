package com.ventureseed.payment.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_transactions")
public class PaymentTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String transactionReference;

    @Column(unique = true)
    private String idempotencyKey;

    @Column(nullable = false)
    private Long backerId;

    @Column(nullable = false)
    private Long campaignId;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false, length = 10)
    private String currency = "USD";

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    private String failureReason;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime settledAt;

    public PaymentTransaction() {
    }

    public PaymentTransaction(String transactionReference, String idempotencyKey, Long backerId,
                              Long campaignId, BigDecimal amount, String currency,
                              PaymentMethod paymentMethod, PaymentStatus status) {
        this.transactionReference = transactionReference;
        this.idempotencyKey = idempotencyKey;
        this.backerId = backerId;
        this.campaignId = campaignId;
        this.amount = amount;
        this.currency = (currency != null) ? currency : "USD";
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.createdAt = LocalDateTime.now();
        if (status == PaymentStatus.SUCCESS) {
            this.settledAt = LocalDateTime.now();
        }
    }

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
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
