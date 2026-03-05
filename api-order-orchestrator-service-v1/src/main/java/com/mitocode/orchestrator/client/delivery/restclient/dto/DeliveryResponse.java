package com.mitocode.orchestrator.client.delivery.restclient.dto;

import java.util.UUID;

public record DeliveryResponse(
        Long id,
        UUID orderId,
        String address,
        String status,
        String deliveryPersonName
) {
}
