package com.ventureseed.payment.controller;

import com.ventureseed.payment.dto.*;
import com.ventureseed.payment.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/charge")
    public ResponseEntity<?> charge(@Valid @RequestBody ChargePaymentRequest request) {
        try {
            PaymentResponse response = paymentService.charge(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PostMapping("/disburse")
    public ResponseEntity<?> disburse(@Valid @RequestBody DisburseRequest request) {
        try {
            DisburseResponse response = paymentService.disburse(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping("/transactions/{reference}")
    public ResponseEntity<?> getTransaction(@PathVariable String reference) {
        try {
            return ResponseEntity.ok(paymentService.getTransactionByReference(reference));
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @GetMapping("/campaign/{campaignId}")
    public ResponseEntity<List<PaymentResponse>> getCampaignTransactions(@PathVariable Long campaignId) {
        return ResponseEntity.ok(paymentService.getTransactionsByCampaignId(campaignId));
    }

    @GetMapping("/backer/{backerId}")
    public ResponseEntity<List<PaymentResponse>> getBackerTransactions(@PathVariable Long backerId) {
        return ResponseEntity.ok(paymentService.getTransactionsByBackerId(backerId));
    }
}
