package com.mitocode.orchestrator.service;

import com.mitocode.orchestrator.client.payments.PaymentServiceV1FeignClient;
import com.mitocode.orchestrator.client.payments.dto.ChargeRequest;
import com.mitocode.orchestrator.client.payments.dto.CheckBalanceRequest;
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
    public void charge(Long customerId, Long cardId, BigDecimal amount) {

        // Simula un insert a la base de datos
        ChargeRequest request = new ChargeRequest(customerId, cardId, amount);
        ResponseEntity<Void> response = paymentServiceClient.charge(request);

        if (response.getStatusCode().isError()){
            throw new RuntimeException("Error charging amount to customerId: " + customerId + ", cardId: " + cardId);
        }

    }

}
