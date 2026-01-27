package com.mitocode.restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItem {
    private final Long productId;
    private final String productName;
    private final int quantity;
    private final String description;
}
