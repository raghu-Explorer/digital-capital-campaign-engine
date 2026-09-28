package com.ventureseed.payment.service;

import com.ventureseed.payment.dto.*;
import com.ventureseed.payment.model.PaymentStatus;
import com.ventureseed.payment.model.PaymentTransaction;
import com.ventureseed.payment.repository.PaymentTransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final PaymentTransactionRepository paymentRepository;

    public PaymentService(PaymentTransactionRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public PaymentResponse charge(ChargePaymentRequest request) {
        // Idempotency check: if request was already processed with same key, return recorded response
        if (request.getIdempotencyKey() != null && !request.getIdempotencyKey().trim().isEmpty()) {
            Optional<PaymentTransaction> existing = paymentRepository.findByIdempotencyKey(request.getIdempotencyKey());
            if (existing.isPresent()) {
                return PaymentResponse.fromEntity(existing.get());
            }
        }

        String txnRef = "txn_" + UUID.randomUUID().toString().replace("-", "").substring(0, 16);

        PaymentTransaction transaction = new PaymentTransaction(
                txnRef,
                request.getIdempotencyKey(),
                request.getBackerId(),
                request.getCampaignId(),
                request.getAmount(),
                request.getCurrency(),
                request.getPaymentMethod(),
                PaymentStatus.SUCCESS
        );

        PaymentTransaction saved = paymentRepository.save(transaction);
        return PaymentResponse.fromEntity(saved);
    }

    @Transactional
    public DisburseResponse disburse(DisburseRequest request) {
        String disbRef = "disb_" + UUID.randomUUID().toString().replace("-", "").substring(0, 16);

        return new DisburseResponse(
                disbRef,
                request.getCampaignId(),
                request.getCreatorId(),
                request.getAmount(),
                "SETTLED",
                request.getDestinationAccount(),
                LocalDateTime.now()
        );
    }

    public PaymentResponse getTransactionByReference(String reference) {
        PaymentTransaction txn = paymentRepository.findByTransactionReference(reference)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found for reference: " + reference));
        return PaymentResponse.fromEntity(txn);
    }

    public List<PaymentResponse> getTransactionsByCampaignId(Long campaignId) {
        return paymentRepository.findByCampaignId(campaignId).stream()
                .map(PaymentResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public List<PaymentResponse> getTransactionsByBackerId(Long backerId) {
        return paymentRepository.findByBackerId(backerId).stream()
                .map(PaymentResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
