package com.ventureseed.payment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ventureseed.payment.dto.ChargePaymentRequest;
import com.ventureseed.payment.model.PaymentMethod;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PaymentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testChargeIntegration() throws Exception {
        ChargePaymentRequest request = new ChargePaymentRequest();
        request.setCampaignId(1L);
        request.setBackerId(2L);
        request.setAmount(BigDecimal.valueOf(500.00));
        request.setPaymentMethod(PaymentMethod.DIGITAL_WALLET);
        request.setIdempotencyKey("test-idemp-999");

        mockMvc.perform(post("/api/payments/charge")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.transactionReference").isNotEmpty())
                .andExpect(jsonPath("$.status").value("SUCCESS"))
                .andExpect(jsonPath("$.amount").value(500.00));
    }
}
