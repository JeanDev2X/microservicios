package com.mitocode.orchestrator.client.restaurants.dto;

public record OrderItemRequest(
        Long productId,
        String productName,
        int quantity,
        String description
) {}
