package com.mitocode.payment.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ChargeRequest(UUID orderId, Long customerId, Long cardId, BigDecimal amount) {
}
