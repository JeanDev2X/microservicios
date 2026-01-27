package com.mitocode.delivery.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Delivery {
    private Long id;
    private UUID orderId;
    private String address;
    private Double latitude;
    private Double longitude;
    private String reference;
    private DeliveryPerson deliveryPerson;
    private DeliveryStatus status;
}
