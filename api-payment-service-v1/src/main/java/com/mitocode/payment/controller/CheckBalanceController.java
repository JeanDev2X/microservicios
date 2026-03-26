package com.mitocode.payment.controller;

import com.mitocode.payment.domain.CheckBalance;
import com.mitocode.payment.dto.CheckBalanceRequest;
import com.mitocode.payment.service.CheckBalanceService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Void> checkBalance(@RequestBody CheckBalanceRequest request, HttpServletRequest servletRequest) {
        String xDemoMode = servletRequest.getHeader("X-Demo-Mode");
        String requestId = servletRequest.getHeader("X-Request-ID");
        log.info("Checking balance with headers: X-Demo-Mode: {}, X-Request-ID: {}", xDemoMode, requestId);

        CheckBalance checkBalance = CheckBalance.createNew(request.customerId(), request.cardId(), request.requiredAmount());
        if (service.checkFunds(checkBalance)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
    }
}
