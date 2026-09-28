package com.ventureseed.contribution.controller;

import com.ventureseed.contribution.dto.ContributionResponse;
import com.ventureseed.contribution.dto.CreateContributionRequest;
import com.ventureseed.contribution.service.ContributionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/contributions")
public class ContributionController {

    private final ContributionService contributionService;

    public ContributionController(ContributionService contributionService) {
        this.contributionService = contributionService;
    }

    @PostMapping
    public ResponseEntity<?> createContribution(@Valid @RequestBody CreateContributionRequest request) {
        try {
            ContributionResponse response = contributionService.contribute(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalStateException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping
    public ResponseEntity<List<ContributionResponse>> getAllContributions() {
        return ResponseEntity.ok(contributionService.getAllContributions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getContributionById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(contributionService.getContributionById(id));
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @GetMapping("/campaign/{campaignId}")
    public ResponseEntity<List<ContributionResponse>> getContributionsByCampaign(@PathVariable Long campaignId) {
        return ResponseEntity.ok(contributionService.getContributionsByCampaign(campaignId));
    }

    @GetMapping("/backer/{backerId}")
    public ResponseEntity<List<ContributionResponse>> getContributionsByBacker(@PathVariable Long backerId) {
        return ResponseEntity.ok(contributionService.getContributionsByBacker(backerId));
    }

    @GetMapping("/stats/total")
    public ResponseEntity<Map<String, Object>> getTotalContributionsStats() {
        BigDecimal total = contributionService.getTotalContributions();
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalContributions", total != null ? total : BigDecimal.ZERO);
        return ResponseEntity.ok(stats);
    }
}
