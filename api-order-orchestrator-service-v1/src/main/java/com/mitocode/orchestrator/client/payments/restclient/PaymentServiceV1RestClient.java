package com.mitocode.orchestrator.client.payments.restclient;

import com.mitocode.orchestrator.client.payments.dto.ChargeRequest;
import com.mitocode.orchestrator.client.payments.dto.CheckBalanceRequest;
import com.mitocode.orchestrator.client.payments.dto.RefundRequest;
import com.mitocode.orchestrator.excepcion.InsufficientFundsException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestClient;

@Component
@AllArgsConstructor
public class PaymentServiceV1RestClient {

    private final RestClient paymentsV1RestClient;

    public ResponseEntity<Void> checkBalance(@RequestBody CheckBalanceRequest request) {

        return paymentsV1RestClient.post()
                .uri("/check-balance")
                .body(request)
                .retrieve()
                .onStatus(
                        httpStatusCode -> httpStatusCode.isSameCodeAs(HttpStatus.PRECONDITION_FAILED),
                        (httpRequest, clientHttpResponse) -> { throw new InsufficientFundsException("Insufficient funds for customerId: " + request.customerId() + ", cardId: " + request.cardId()); }
                )
                .toBodilessEntity();
    }

    public ResponseEntity<Void> charge(@RequestBody ChargeRequest request){
        return paymentsV1RestClient.post()
                .uri("/charge")
                .body(request)
                .retrieve()
                .toBodilessEntity();

    }

    public ResponseEntity<Void> refund(@RequestBody RefundRequest request) {

        return paymentsV1RestClient.post()
                .uri("/refund")
                .body(request)
                .retrieve()
                .toBodilessEntity();
    }

}
