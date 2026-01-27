package com.mitocode.payment.dto;

import java.math.BigDecimal;

public record ChargeRequest(Long customerId, Long cardId, BigDecimal amount) {
}
