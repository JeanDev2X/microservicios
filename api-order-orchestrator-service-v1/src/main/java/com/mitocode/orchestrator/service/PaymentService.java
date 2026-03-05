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

    //realizar el cargo
    @CircuitBreaker(name = "chargePaymentV2CB", fallbackMethod = "chargeFallback")
    public void charge(Long customerId, Long cardId, BigDecimal amount) {
        log.info("Calling PaymentServiceV2#charge");
        // Simula un insert a la base de datos
        ChargeRequest request = new ChargeRequest(customerId, cardId, amount);
        ResponseEntity<Void> response = paymentServiceV2RestClient.charge(request);

        if (response.getStatusCode().isError()){
            throw new RuntimeException("Error charging amount to customerId: " + customerId + ", cardId: " + cardId);
        }
    }

    //realizar el cargo
    //El fallback method tiene que tener la misma firma del metodo anotado con @CircuitBreaker + un parametro adicional de tipo Throwable
    public void chargeFallback(Long customerId, Long cardId, BigDecimal amount,Throwable ex) {
        log.info("Calling Fallback PaymentServiceV1#charge");
        // Simula un insert a la base de datos
        //validacion si ya se hizo el insert del metodo de arriba no hacer nada
        ChargeRequest request = new ChargeRequest(customerId, cardId, amount);
        ResponseEntity<Void> response = paymentServiceV1RestClient.charge(request);

        if (response.getStatusCode().isError()){
            throw new RuntimeException("Error charging amount to customerId: " + customerId + ", cardId: " + cardId);
        }
    }

}
