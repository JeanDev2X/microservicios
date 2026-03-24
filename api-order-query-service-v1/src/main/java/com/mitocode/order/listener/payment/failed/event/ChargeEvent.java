package com.mitocode.order.listener.payment.failed.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ChargeEvent {
    private final Long customerId;
    private final Long cardId;
    private final BigDecimal amount;
}
