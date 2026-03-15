package com.mitocode.order.infraestructure.document;

import lombok.Data;

@Data
public class DeliveryPersonDocument {
    private Long id;
    private String name;
    private String phoneNumber;
    private String vehicleType;
    private String licensePlate;
}
