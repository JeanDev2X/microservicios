package com.mitocode.dto;

public record OrderItemResponse(Long id, ProductResponse product, Integer quantity) {
}
