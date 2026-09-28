package com.ventureseed.contribution.client;

import com.ventureseed.contribution.dto.PaymentChargeRequest;
import com.ventureseed.contribution.dto.PaymentResultDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service")
public interface PaymentClient {

    @PostMapping("/api/payments/charge")
    PaymentResultDto chargePayment(@RequestBody PaymentChargeRequest request);
}
