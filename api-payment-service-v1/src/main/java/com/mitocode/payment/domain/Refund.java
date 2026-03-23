package com.mitocode.payment.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Refund {
    private final Long customerId;
    private final Long cardId;
    private final BigDecimal amount;
}
