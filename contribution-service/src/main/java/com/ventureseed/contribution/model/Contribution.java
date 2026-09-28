package com.ventureseed.contribution.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "contributions")
public class Contribution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long campaignId;

    @Column(nullable = false)
    private Long backerId;

    @Column(nullable = false)
    private String backerName;

    @Column(nullable = false)
    private String backerEmail;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private String paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContributionStatus status;

    private String transactionReference;

    @Column(length = 1000)
    private String message;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Contribution() {
    }

    public Contribution(Long campaignId, Long backerId, String backerName, String backerEmail,
                        BigDecimal amount, String paymentMethod, ContributionStatus status,
                        String transactionReference, String message) {
        this.campaignId = campaignId;
        this.backerId = backerId;
        this.backerName = backerName;
        this.backerEmail = backerEmail;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.transactionReference = transactionReference;
        this.message = message;
        this.createdAt = LocalDateTime.now();
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

    public ContributionStatus getStatus() {
        return status;
    }

    public void setStatus(ContributionStatus status) {
        this.status = status;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
