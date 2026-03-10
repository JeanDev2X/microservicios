package com.mitocode.orchestrator.service;

import com.mitocode.orchestrator.client.payments.feign.PaymentServiceV1FeignClient;
import com.mitocode.orchestrator.client.payments.dto.ChargeRequest;
import com.mitocode.orchestrator.client.payments.dto.CheckBalanceRequest;
import com.mitocode.orchestrator.client.payments.restclient.PaymentServiceV1RestClient;
import com.mitocode.orchestrator.client.payments.restclient.PaymentServiceV2RestClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@AllArgsConstructor
public class PaymentService {

    private final PaymentServiceV1FeignClient paymentServiceClient;
    private final PaymentServiceV1RestClient paymentServiceV1RestClient;
    private final PaymentServiceV2RestClient paymentServiceV2RestClient;

    //validar si el cliente tiene saldo suficiente
    public void checkBalance(Long customerId, Long cardId, BigDecimal amount) {
        log.info("Checking balance for customerId: {}, cardId: {}, amount: {}", customerId, cardId, amount);

        CheckBalanceRequest request = new CheckBalanceRequest(customerId, cardId, amount);
        ResponseEntity<Void> response = paymentServiceClient.checkBalance(request);

        if (response.getStatusCode().is4xxClientError()) {
            throw new RuntimeException("Insufficient funds for customerId: " + customerId + ", cardId: " + cardId);
        }

        log.info("Sufficient funds available for customerId: {}, cardId: {}, amount: {}", customerId, cardId, amount);
    }

    //realizar el cargo.
    //comenta el @CircuitBreaker para probar el fallback, luego descomenta para volver a la implementación original
    @CircuitBreaker(name = "chargePaymentV2CB", fallbackMethod = "chargeFallback")
    public void charge(Long customerId, Long cardId, BigDecimal amount) {
        log.info("Calling PaymentServiceV2#charge");

        // Simula un insert a la base de datos
        ChargeRequest request = new ChargeRequest(customerId, cardId, amount);
        ResponseEntity<Void> response = paymentServiceV2RestClient.charge(request);

//        ResponseEntity<Void> response;
//        try {
//            response = paymentServiceV2RestClient.charge(request);
//        } catch (Exception ex) {
//            throw new PaymentFailedException("PaymentServiceV2 is not available", ex);
//        }

        if (response.getStatusCode().isError()){
            throw new RuntimeException("Error charging amount to customerId: " + customerId + ", cardId: " + cardId);
        }

        log.info("Successfully charged amount for customerId: {}, cardId: {}, amount: {}", customerId, cardId, amount);
    }

    //realizar el cargo
    public void chargeFallback(Long customerId, Long cardId, BigDecimal amount,Throwable ex) {
        log.info("Calling Fallback PaymentServiceV1#charge");
        // Simula un insert a la base de datos
        ChargeRequest request = new ChargeRequest(customerId, cardId, amount);
        ResponseEntity<Void> response = paymentServiceV1RestClient.charge(request);

        if (response.getStatusCode().isError()){
            throw new RuntimeException("Error charging amount to customerId: " + customerId + ", cardId: " + cardId);
        }
        log.info("Successfully charged amount for customerId: {}, cardId: {}, amount: {}", customerId, cardId, amount);
    }

}
