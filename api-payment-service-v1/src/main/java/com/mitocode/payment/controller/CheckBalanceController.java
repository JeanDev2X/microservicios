package com.mitocode.payment.controller;

import com.mitocode.payment.domain.CheckBalance;
import com.mitocode.payment.dto.CheckBalanceRequest;
import com.mitocode.payment.service.CheckBalanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/payments")
public class CheckBalanceController {

    private final CheckBalanceService service;

    public CheckBalanceController(CheckBalanceService service) {
        this.service = service;
    }

    @PostMapping("/check-balance")
    public ResponseEntity<Void> checkBalance(@RequestBody CheckBalanceRequest request) {
        log.info("Checking balance");
        CheckBalance checkBalance = CheckBalance.createNew(request.customerId(), request.cardId(), request.requiredAmount());
        if (service.checkFunds(checkBalance)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
