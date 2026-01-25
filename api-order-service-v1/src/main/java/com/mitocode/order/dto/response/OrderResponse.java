package com.mitocode.order.dto.response;

import com.mitocode.order.domain.OrderStatus;

import java.util.UUID;

public record OrderResponse(UUID id, OrderStatus status) {
}
