package com.mitocode.order.dto.request;

import com.mitocode.order.domain.OrderStatus;

public record UpdateOrderStatusRequest(OrderStatus status) {
}
