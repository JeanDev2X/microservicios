package com.mitocode.orchestrator.client.delivery.restclient.dto;

import java.util.UUID;

public record AssignDriverRequest(
        UUID orderId,
        DeliveryAddressRequest deliveryAddress,
        DeliveryPersonRequest deliveryPerson
) {
}
