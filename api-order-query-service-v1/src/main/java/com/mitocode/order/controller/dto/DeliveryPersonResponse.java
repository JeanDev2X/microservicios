package com.mitocode.order.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeliveryPersonResponse {
    private Long id;
    private String name;
    private String phoneNumber;
    private String vehicleType;
    private String licensePlate;
}
