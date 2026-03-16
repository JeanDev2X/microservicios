package com.mitocode.order.infraestructure.document;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentDocument {
    private CardDocument card;
    private BigDecimal amount;
    private String status;
    private String description;
}
