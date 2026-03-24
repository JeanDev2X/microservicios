package com.mitocode.order.infraestructure.document;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RefundDocument {
    private CardDocument card;
    private BigDecimal amount;
}
