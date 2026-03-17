package com.mitocode.restaurant.producer.restaurant.order.reserved.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItemEvent {
    private Long productId;
    private String productName;
    private int quantity;
    private String description;
}
