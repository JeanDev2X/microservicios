package com.mitocode.delivery.controller.dto;

public record DeliveryAddressRequest(
        String address,
        Double latitude,
        Double longitude,
        String reference
) {
}
