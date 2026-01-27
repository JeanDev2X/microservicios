package com.mitocode.payment.service;

import com.mitocode.payment.client.VisaRestTemplateClient;
import com.mitocode.payment.client.dto.CheckBalanceResponse;
import com.mitocode.payment.domain.CheckBalance;
import com.mitocode.payment.repository.CheckBalanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CheckBalanceService {

    private final CheckBalanceRepository repository;
    private final VisaRestTemplateClient visaClient;

    public boolean checkFunds(CheckBalance checkBalance) {

        CheckBalanceResponse response = visaClient.checkBalance(checkBalance.getCardId(), checkBalance.getRequiredAmount());

        checkBalance.setId(UUID.randomUUID());

        return repository.saveCheckResult(checkBalance, response.sufficient());
    }
}
