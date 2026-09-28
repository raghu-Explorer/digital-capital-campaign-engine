package com.ventureseed.contribution.dto;

import java.math.BigDecimal;

public class ContributeInternalDto {

    private BigDecimal amount;
    private Long backerId;

    public ContributeInternalDto() {
    }

    public ContributeInternalDto(BigDecimal amount, Long backerId) {
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
