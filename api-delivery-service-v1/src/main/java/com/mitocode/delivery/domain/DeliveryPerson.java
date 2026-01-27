package com.mitocode.delivery.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeliveryPerson {
    private Long id;
    private String name;
    private String phoneNumber;
    private String vehicleType;
    private String licensePlate;

    public DeliveryPerson(Long id) {
        this.id = id;
    }
}
