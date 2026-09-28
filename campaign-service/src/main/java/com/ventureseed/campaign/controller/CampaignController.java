package com.ventureseed.campaign.controller;

import com.ventureseed.campaign.dto.*;
import com.ventureseed.campaign.model.CampaignCategory;
import com.ventureseed.campaign.model.CampaignStatus;
import com.ventureseed.campaign.service.CampaignService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

    private final CampaignService campaignService;

    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @PostMapping
    public ResponseEntity<?> createCampaign(@Valid @RequestBody CreateCampaignRequest request) {
        try {
            CampaignResponse response = campaignService.createCampaign(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping
    public ResponseEntity<List<CampaignResponse>> getAllCampaigns(
            @RequestParam(required = false) CampaignStatus status,
            @RequestParam(required = false) CampaignCategory category,
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(campaignService.getAllCampaigns(status, category, search));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCampaignById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(campaignService.getCampaignById(id));
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCampaign(@PathVariable Long id, @RequestBody UpdateCampaignRequest request) {
        try {
            return ResponseEntity.ok(campaignService.updateCampaign(id, request));
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @PostMapping("/{id}/contribute-internal")
    public ResponseEntity<?> processContributionInternal(@PathVariable Long id,
                                                         @Valid @RequestBody ContributeInternalRequest request) {
        try {
            CampaignResponse response = campaignService.processContributionInternal(id, request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (IllegalStateException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }
    }

    @GetMapping("/{id}/validate-active")
    public ResponseEntity<Map<String, Object>> validateActive(@PathVariable Long id) {
        try {
            boolean active = campaignService.validateCampaignActive(id);
            Map<String, Object> resp = new HashMap<>();
            resp.put("campaignId", id);
            resp.put("active", active);
            return ResponseEntity.ok(resp);
        } catch (IllegalArgumentException e) {
            Map<String, Object> resp = new HashMap<>();
            resp.put("campaignId", id);
            resp.put("active", false);
            resp.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
        }
    }

    @GetMapping("/metrics")
    public ResponseEntity<PlatformMetricsResponse> getPlatformMetrics() {
        return ResponseEntity.ok(campaignService.getPlatformMetrics());
    }
}
