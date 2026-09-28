package com.ventureseed.payment.repository;

import com.ventureseed.payment.model.PaymentStatus;
import com.ventureseed.payment.model.PaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, Long> {

    Optional<PaymentTransaction> findByTransactionReference(String transactionReference);

    Optional<PaymentTransaction> findByIdempotencyKey(String idempotencyKey);

    List<PaymentTransaction> findByCampaignId(Long campaignId);

    List<PaymentTransaction> findByBackerId(Long backerId);

    List<PaymentTransaction> findByStatus(PaymentStatus status);
}
