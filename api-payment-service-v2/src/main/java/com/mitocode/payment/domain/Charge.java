package com.mitocode.payment.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.With;

import java.math.BigDecimal;

@With
@Data
@Builder(toBuilder = true)
public class Charge {
    private final Long customerId;
    private final Long cardId;
    private final BigDecimal amount;
    private final String status;
}
