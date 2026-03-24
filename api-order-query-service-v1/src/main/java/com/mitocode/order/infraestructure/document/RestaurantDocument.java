package com.mitocode.order.infraestructure.document;

import lombok.Data;

@Data
public class RestaurantDocument {
    private Long id;
    private String name;
    private String orderStatus;
    private String reason;
}
