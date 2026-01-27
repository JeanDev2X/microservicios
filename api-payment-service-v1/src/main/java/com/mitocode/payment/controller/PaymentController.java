package com.mitocode.payment.controller;

import com.mitocode.payment.domain.Charge;
import com.mitocode.payment.dto.ChargeRequest;
import com.mitocode.payment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping("/charge")
    public void charge(@RequestBody ChargeRequest chargeRequest) {
        log.info("Charging amount");
        // request -> dominio
        Charge charge = new Charge(chargeRequest.customerId(), chargeRequest.cardId(), chargeRequest.amount());
        service.charge(charge);
    }

}
