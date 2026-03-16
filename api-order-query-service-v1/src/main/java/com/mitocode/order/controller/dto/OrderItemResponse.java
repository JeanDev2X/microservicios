package com.mitocode.order.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemResponse {
    private ProductResponse product;
    private int quantity;
    private String description;
}
