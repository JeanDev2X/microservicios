package com.mitocode.restaurant.dto;

public record OrderItemRequest(
        Long productId,
        String productName,
        int quantity,
        String description
) {}
