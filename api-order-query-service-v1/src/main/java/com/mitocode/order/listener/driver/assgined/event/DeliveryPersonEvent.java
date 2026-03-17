package com.mitocode.order.listener.driver.assgined.event;

import lombok.Data;

@Data
public class DeliveryPersonEvent {
    private Long id;
    private String name;
    private String phoneNumber;
    private String vehicleType;
    private String licensePlate;
}
