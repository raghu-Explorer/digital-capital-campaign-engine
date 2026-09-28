package com.ventureseed.payment.service;

import com.ventureseed.payment.dto.ChargePaymentRequest;
import com.ventureseed.payment.dto.PaymentResponse;
import com.ventureseed.payment.model.PaymentMethod;
import com.ventureseed.payment.model.PaymentStatus;
import com.ventureseed.payment.model.PaymentTransaction;
import com.ventureseed.payment.repository.PaymentTransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private PaymentTransactionRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    private PaymentTransaction testTxn;

    @BeforeEach
    void setUp() {
        testTxn = new PaymentTransaction(
                "txn_1234567890123456",
                "idemp-key-101",
                2L,
                1L,
                BigDecimal.valueOf(250.00),
                "USD",
                PaymentMethod.CREDIT_CARD,
                PaymentStatus.SUCCESS
        );
        testTxn.setId(1L);
    }

    @Test
    void testCharge_Success() {
        ChargePaymentRequest request = new ChargePaymentRequest();
        request.setCampaignId(1L);
        request.setBackerId(2L);
        request.setAmount(BigDecimal.valueOf(250.00));
        request.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        request.setIdempotencyKey("idemp-key-new");

        when(paymentRepository.findByIdempotencyKey("idemp-key-new")).thenReturn(Optional.empty());
        when(paymentRepository.save(any(PaymentTransaction.class))).thenReturn(testTxn);

        PaymentResponse response = paymentService.charge(request);

        assertNotNull(response);
        assertEquals(PaymentStatus.SUCCESS, response.getStatus());
        assertEquals(BigDecimal.valueOf(250.00), response.getAmount());
        verify(paymentRepository, times(1)).save(any(PaymentTransaction.class));
    }

    @Test
    void testCharge_IdempotentDuplicateReturnsExisting() {
        ChargePaymentRequest request = new ChargePaymentRequest();
        request.setCampaignId(1L);
        request.setBackerId(2L);
        request.setAmount(BigDecimal.valueOf(250.00));
        request.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        request.setIdempotencyKey("idemp-key-101");

        when(paymentRepository.findByIdempotencyKey("idemp-key-101")).thenReturn(Optional.of(testTxn));

        PaymentResponse response = paymentService.charge(request);

        assertNotNull(response);
        assertEquals("txn_1234567890123456", response.getTransactionReference());
        verify(paymentRepository, never()).save(any(PaymentTransaction.class));
    }
}
