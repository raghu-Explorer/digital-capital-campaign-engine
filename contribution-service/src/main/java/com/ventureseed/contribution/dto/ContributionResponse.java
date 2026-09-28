package com.ventureseed.contribution.dto;

import com.ventureseed.contribution.model.Contribution;
import com.ventureseed.contribution.model.ContributionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ContributionResponse {

    private Long id;
    private Long campaignId;
    private Long backerId;
    private String backerName;
    private String backerEmail;
    private BigDecimal amount;
    private String paymentMethod;
    private ContributionStatus status;
    private String transactionReference;
    private String message;
    private LocalDateTime createdAt;

    public ContributionResponse() {
    }

    public static ContributionResponse fromEntity(Contribution c) {
        ContributionResponse resp = new ContributionResponse();
        resp.setId(c.getId());
        resp.setCampaignId(c.getCampaignId());
        resp.setBackerId(c.getBackerId());
        resp.setBackerName(c.getBackerName());
        resp.setBackerEmail(c.getBackerEmail());
        resp.setAmount(c.getAmount());
        resp.setPaymentMethod(c.getPaymentMethod());
        resp.setStatus(c.getStatus());
        resp.setTransactionReference(c.getTransactionReference());
        resp.setMessage(c.getMessage());
        resp.setCreatedAt(c.getCreatedAt());
        return resp;
    }

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
