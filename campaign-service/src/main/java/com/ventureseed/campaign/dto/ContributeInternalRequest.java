package com.ventureseed.campaign.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ContributeInternalRequest {

    @NotNull(message = "Contribution amount is required")
    @DecimalMin(value = "1.00", message = "Contribution amount must be at least 1.00")
    private BigDecimal amount;

    @NotNull(message = "Backer ID is required")
    private Long backerId;

    public ContributeInternalRequest() {
    }

    public ContributeInternalRequest(BigDecimal amount, Long backerId) {
        this.amount = amount;
        this.backerId = backerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getBackerId() {
        return backerId;
    }

    public void setBackerId(Long backerId) {
        this.backerId = backerId;
    }
}
