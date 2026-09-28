package com.ventureseed.campaign.dto;

import java.math.BigDecimal;

public class PlatformMetricsResponse {

    private BigDecimal totalRaised;
    private long totalCampaigns;
    private long activeCampaigns;
    private long fundedCampaigns;
    private long totalBackers;

    public PlatformMetricsResponse() {
    }

    public PlatformMetricsResponse(BigDecimal totalRaised, long totalCampaigns, long activeCampaigns,
                                   long fundedCampaigns, long totalBackers) {
        this.totalRaised = totalRaised;
        this.totalCampaigns = totalCampaigns;
        this.activeCampaigns = activeCampaigns;
        this.fundedCampaigns = fundedCampaigns;
        this.totalBackers = totalBackers;
    }

    public BigDecimal getTotalRaised() {
        return totalRaised;
    }

    public void setTotalRaised(BigDecimal totalRaised) {
        this.totalRaised = totalRaised;
    }

    public long getTotalCampaigns() {
        return totalCampaigns;
    }

    public void setTotalCampaigns(long totalCampaigns) {
        this.totalCampaigns = totalCampaigns;
    }

    public long getActiveCampaigns() {
        return activeCampaigns;
    }

    public void setActiveCampaigns(long activeCampaigns) {
        this.activeCampaigns = activeCampaigns;
    }

    public long getFundedCampaigns() {
        return fundedCampaigns;
    }

    public void setFundedCampaigns(long fundedCampaigns) {
        this.fundedCampaigns = fundedCampaigns;
    }

    public long getTotalBackers() {
        return totalBackers;
    }

    public void setTotalBackers(long totalBackers) {
        this.totalBackers = totalBackers;
    }
}
