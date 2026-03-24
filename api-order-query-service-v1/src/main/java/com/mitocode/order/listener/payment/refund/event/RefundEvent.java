package com.mitocode.order.listener.payment.refund.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class RefundEvent {
    private final Long customerId;
    private final Long cardId;
    private final BigDecimal amount;
}
