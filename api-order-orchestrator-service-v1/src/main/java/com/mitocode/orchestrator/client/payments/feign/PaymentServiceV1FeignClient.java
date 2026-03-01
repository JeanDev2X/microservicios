package com.mitocode.orchestrator.client.payments.feign;

import com.mitocode.orchestrator.client.payments.dto.ChargeRequest;
import com.mitocode.orchestrator.client.payments.dto.CheckBalanceRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "api-payment-service-v1")
public interface PaymentServiceV1FeignClient {

    @PostMapping("/api/v1/payments/check-balance")
    ResponseEntity<Void> checkBalance(@RequestBody CheckBalanceRequest request);

    @PostMapping("/api/v1/payments/charge")
    ResponseEntity<Void> charge(@RequestBody ChargeRequest request);

}
