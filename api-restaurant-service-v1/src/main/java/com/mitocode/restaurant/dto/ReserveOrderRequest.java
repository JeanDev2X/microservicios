package com.mitocode.restaurant.dto;

import java.util.List;
import java.util.UUID;

public record ReserveOrderRequest(
        UUID orderId,
        Long restaurantId,
        Long customerId,
        List<OrderItemRequest> items
) {}
