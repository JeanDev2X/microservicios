package com.mitocode.order.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeliveryAddressResponse {
    private String address;
    private double latitude;
    private double longitude;
    private String reference;
}
