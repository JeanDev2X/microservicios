package com.mitocode.orchestrator.client.payments.dto;

import java.math.BigDecimal;

public record ChargeRequest(Long customerId, Long cardId, BigDecimal amount) {
}
