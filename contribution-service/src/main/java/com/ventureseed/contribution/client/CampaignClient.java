package com.ventureseed.contribution.client;

import com.ventureseed.contribution.dto.CampaignDto;
import com.ventureseed.contribution.dto.ContributeInternalDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "campaign-service")
public interface CampaignClient {

    @GetMapping("/api/campaigns/{id}/validate-active")
    Map<String, Object> validateActive(@PathVariable("id") Long id);

    @PostMapping("/api/campaigns/{id}/contribute-internal")
    CampaignDto updateCampaignFunding(@PathVariable("id") Long id, @RequestBody ContributeInternalDto request);
}
