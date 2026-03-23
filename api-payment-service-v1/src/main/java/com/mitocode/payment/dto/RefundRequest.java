package com.mitocode.payment.dto;

import java.math.BigDecimal;

public record RefundRequest(String orderId, Long customerId, Long cardId, BigDecimal amount) {
}
