package com.mitocode.restaurant.dto;

public record OrderItemResponse(
        Long productId,
        String productName,
        int quantity,
        String description
) {
}