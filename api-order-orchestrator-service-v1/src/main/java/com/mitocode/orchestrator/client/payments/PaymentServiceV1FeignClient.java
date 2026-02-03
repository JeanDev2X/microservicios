package com.mitocode.orchestrator.client.payments;

import com.mitocode.orchestrator.client.payments.dto.ChargeRequest;
import com.mitocode.orchestrator.client.payments.dto.CheckBalanceRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "paymentServiceV1FeignClient", url = "http://localhost:50020/api/v1/")
public interface PaymentServiceV1FeignClient {

    @PostMapping("/payments/check-balance")
    ResponseEntity<Void> checkBalance(@RequestBody CheckBalanceRequest request);

    @PostMapping("/payments/charge")
    ResponseEntity<Void> charge(@RequestBody ChargeRequest request);

}
