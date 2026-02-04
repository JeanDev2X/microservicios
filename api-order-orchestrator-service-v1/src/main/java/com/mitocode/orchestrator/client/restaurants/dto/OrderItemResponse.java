package com.mitocode.orchestrator.client.restaurants.dto;

public record OrderItemResponse(
        Long productId,
        String productName,
        int quantity,
        String description
) {
}