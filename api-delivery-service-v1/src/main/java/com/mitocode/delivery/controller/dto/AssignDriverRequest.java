package com.mitocode.delivery.controller.dto;

import java.util.UUID;

public record AssignDriverRequest(
        UUID orderId,
        DeliveryAddressRequest deliveryAddress,
        DeliveryPersonRequest deliveryPerson
) {
}
