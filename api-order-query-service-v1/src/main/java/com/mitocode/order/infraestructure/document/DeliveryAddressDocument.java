package com.mitocode.order.infraestructure.document;

import lombok.Data;

@Data
public class DeliveryAddressDocument {
    private String address;
    private double latitude;
    private double longitude;
    private String reference;
}
