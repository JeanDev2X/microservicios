package com.mitocode.orchestrator.client.payments.restclient;

import com.mitocode.orchestrator.client.payments.dto.ChargeRequest;
import com.mitocode.orchestrator.client.payments.dto.CheckBalanceRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestClient;

@Component
@AllArgsConstructor
public class PaymentServiceV2RestClient {

    private final RestClient paymentsV2RestClient;

    public ResponseEntity<Void> checkBalance(@RequestBody CheckBalanceRequest request){
        return paymentsV2RestClient.post()
                .uri("/check-balance")
                .body(request)
                .retrieve()
                .toBodilessEntity();
    }

    public ResponseEntity<Void> charge(@RequestBody ChargeRequest request){
        return paymentsV2RestClient.post()
                .uri("/charge")
                .body(request)
                .retrieve()
                .toBodilessEntity();

    }

}
