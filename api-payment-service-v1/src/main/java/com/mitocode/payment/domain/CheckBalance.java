package com.mitocode.payment.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CheckBalance {

    private UUID id;
    private Long customerId;
    private Long cardId;
    private BigDecimal requiredAmount;

    public static CheckBalance createNew(Long customerId, Long cardId, BigDecimal requiredAmount) {
        return new CheckBalance(null, customerId, cardId, requiredAmount);
    }


}
