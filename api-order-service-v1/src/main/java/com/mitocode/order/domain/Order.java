package com.mitocode.order.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Order {

    private UUID id;
    private Customer customer;
    private Restaurant restaurant;
    private BigDecimal total;
    private OrderStatus status;
    private String cancelReason;

    // Factory for creation
    public static Order createNew(Customer customer, Restaurant restaurant, BigDecimal total) {
        return new Order(null, customer, restaurant, total, OrderStatus.CREATED, null);
    }
}
