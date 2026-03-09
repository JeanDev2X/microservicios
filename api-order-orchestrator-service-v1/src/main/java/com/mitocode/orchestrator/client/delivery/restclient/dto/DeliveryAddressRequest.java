package com.mitocode.orchestrator.client.delivery.restclient.dto;

public record DeliveryAddressRequest(
        String address,
        Double latitude,
        Double longitude,
        String reference
) {
}
