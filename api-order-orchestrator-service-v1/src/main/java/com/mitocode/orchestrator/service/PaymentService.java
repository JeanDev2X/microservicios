package com.mitocode.orchestrator.service;

import com.mitocode.orchestrator.client.payments.feign.PaymentServiceV1FeignClient;
import com.mitocode.orchestrator.client.payments.dto.ChargeRequest;
import com.mitocode.orchestrator.client.payments.dto.CheckBalanceRequest;
import com.mitocode.orchestrator.client.payments.restclient.PaymentServiceV1RestClient;
import com.mitocode.orchestrator.client.payments.restclient.PaymentServiceV2RestClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

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

    //@RateLimiter(name = "chargeRateLimiter", fallbackMethod = "chargeFallback")
    //realizar el cargo.
    //@Retry(name = "defaultRetry", fallbackMethod = "chargeFallback")
    //comenta el @CircuitBreaker para probar el fallback, luego descomenta para volver a la implementación original
    @CircuitBreaker(name = "chargePaymentV2CB", fallbackMethod = "chargeFallback")
    public void charge(UUID orderId, Long customerId, Long cardId, BigDecimal amount) {
        log.info("Calling PaymentServiceV2#charge");

        // Simula un insert a la base de datos
        ChargeRequest request = new ChargeRequest(orderId,customerId, cardId, amount);
        ResponseEntity<Void> response = paymentServiceV2RestClient.charge(request);

        //De preferencia propagar las excepciones de tipo 5XX para que CircuitBreaker las capture
//        if (response.getStatusCode().is5xxServerError()) {
//            throw new RuntimeException("PaymentServiceV2 is not available");
//        }

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

    //El metodo anotado con circuit breaker siempre debe ser publico por AOP
    //Retry y CircuitBreaker no funcionan juntos por que CircuitBreaker captura primero el error y lo maneja con su fallback, debe funcionar uno por uno
    //El fallback method tiene que tener la misma firma del metodo anotado con @CircuitBreaker + un parametro adicional de tipo Throwable
    //realizar el cargo
    public void chargeFallback(UUID orderId,Long customerId, Long cardId, BigDecimal amount,Throwable ex) {
        log.info("Calling Fallback PaymentServiceV1#charge");
        // Simula un insert a la base de datos
        ChargeRequest request = new ChargeRequest(orderId, customerId, cardId, amount);
        ResponseEntity<Void> response = paymentServiceV1RestClient.charge(request);

        if (response.getStatusCode().isError()){
            throw new RuntimeException("Error charging amount to customerId: " + customerId + ", cardId: " + cardId);
        }
        log.info("Successfully charged amount for customerId: {}, cardId: {}, amount: {}", customerId, cardId, amount);
    }

}
