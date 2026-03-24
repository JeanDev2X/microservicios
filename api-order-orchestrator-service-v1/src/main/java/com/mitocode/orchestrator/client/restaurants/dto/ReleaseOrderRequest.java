package com.mitocode.orchestrator.client.restaurants.dto;

import java.util.UUID;

public record ReleaseOrderRequest(
        UUID orderId
) {
}
