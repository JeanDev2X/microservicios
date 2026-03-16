package com.mitocode.payment.controller;

import com.mitocode.payment.chaos.ChaosConfig;
import com.mitocode.payment.chaos.ChaosState;
import com.mitocode.payment.chaos.ChaosType;
import com.mitocode.payment.domain.Charge;
import com.mitocode.payment.dto.ChargeRequest;
import com.mitocode.payment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final ChaosState chaosState = new ChaosState();

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping("/chaos")
    public ResponseEntity<Void> configureChaos(@RequestBody ChaosConfig chaosConfig) {
        chaosState.update(chaosConfig);
        return ResponseEntity.ok().build();
    }

    private void applyChaos() throws InterruptedException {
        ChaosConfig chaos = chaosState.getConfig();

        if (!chaos.enabled() || chaos.type() == ChaosType.NONE) {
            return;
        }

        if (chaos.type() == ChaosType.DELAY) {
            Thread.sleep(chaos.delayMs());
        }

        if (chaos.type() == ChaosType.ERROR) {
            throw new RuntimeException("Chaos: forced error");
        }

        if (chaos.type() == ChaosType.ERROR_N_TIMES && chaosState.shouldFail()) {
            throw new RuntimeException("Chaos: temporary error");
        }
    }

    @PostMapping("/charge")
    public void charge(@RequestBody ChargeRequest chargeRequest) throws InterruptedException {
        log.info("Charging amount");

        applyChaos();

        // request -> dominio
        Charge charge = Charge.builder()
                .cardId(chargeRequest.cardId())
                .customerId(chargeRequest.customerId())
                .amount(chargeRequest.amount()).build();

        service.charge(chargeRequest.orderId().toString(), charge);
    }
}
