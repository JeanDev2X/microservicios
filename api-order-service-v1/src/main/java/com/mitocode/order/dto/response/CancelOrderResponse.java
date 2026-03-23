package com.mitocode.order.dto.response;

import com.mitocode.order.domain.OrderStatus;

public record CancelOrderResponse(OrderStatus status, String reason) {
}
