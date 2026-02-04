package com.mitocode.orchestrator.client.restaurants.dto;

import java.util.List;
import java.util.UUID;

public record ReserveOrderResponse(
        UUID orderId,
        Long restaurantId,
        Long customerId,
        List<OrderItemResponse> items
) {
}
