package com.mitocode.orchestrator.client.payments.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ChargeRequest(UUID orderId, Long customerId, Long cardId, BigDecimal amount) {
}

