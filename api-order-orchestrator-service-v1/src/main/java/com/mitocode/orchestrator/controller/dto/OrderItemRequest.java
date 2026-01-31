package com.mitocode.orchestrator.controller.dto;

import java.math.BigDecimal;

public record OrderItemRequest(Long id, ProductRequest product, int quantity, String description, BigDecimal price) {
}
