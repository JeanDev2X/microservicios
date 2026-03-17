package com.mitocode.delivery.producer.driver.assigned.event;

import lombok.Data;

@Data
public class DriverAssignedEvent {
    private String orderId;
    private String address;
    private Double latitude;
    private Double longitude;
    private String reference;
    private DeliveryPersonEvent deliveryPerson;
    private String status;
}
