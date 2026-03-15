package com.mitocode.order.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PaymentResponse {
    private CardResponse card;
    private BigDecimal amount;
    private String status;
    private String description;
}
