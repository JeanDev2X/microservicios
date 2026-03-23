package com.mitocode.restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private UUID orderId;
    private Restaurant restaurant;
    private Customer customer;
    private List<OrderItem> items;
    private String status;
    private String reason;
}
