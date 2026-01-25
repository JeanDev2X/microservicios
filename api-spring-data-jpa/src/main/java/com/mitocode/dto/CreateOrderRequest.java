package com.mitocode.dto;

import java.time.LocalDateTime;
import java.util.List;

public record CreateOrderRequest(
        LocalDateTime date,
        Long clientId,
        String status,
        List<Item> items
) {
    public record Item(Long productId, Integer quantity) {}
}
