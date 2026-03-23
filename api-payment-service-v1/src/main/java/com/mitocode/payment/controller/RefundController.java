package com.mitocode.payment.controller;

import com.mitocode.payment.domain.Refund;
import com.mitocode.payment.dto.RefundRequest;
import com.mitocode.payment.service.RefundService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/payments")
public class RefundController {

    private RefundService service;

    @PostMapping("/refund")
    public ResponseEntity<Void> refund(@RequestBody RefundRequest request) {
        log.info("Doing the refund of the charge: {}", request.amount());
        Refund refund = new Refund(request.customerId(), request.cardId(), request.amount());
        service.refund(request.orderId(), refund);
        return ResponseEntity.ok().build();
    }
}
