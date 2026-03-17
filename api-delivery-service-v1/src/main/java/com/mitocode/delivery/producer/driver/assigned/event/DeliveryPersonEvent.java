package com.mitocode.delivery.producer.driver.assigned.event;

import lombok.Data;

@Data
public class DeliveryPersonEvent {
    private Long id;
    private String name;
    private String phoneNumber;
    private String vehicleType;
    private String licensePlate;
}
