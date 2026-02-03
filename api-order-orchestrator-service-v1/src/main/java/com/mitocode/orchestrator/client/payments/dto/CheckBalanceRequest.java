package com.mitocode.orchestrator.client.payments.dto;

import java.math.BigDecimal;

public record CheckBalanceRequest(Long customerId, Long cardId, BigDecimal requiredAmount) {
}