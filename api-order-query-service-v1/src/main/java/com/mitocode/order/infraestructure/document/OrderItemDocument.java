package com.mitocode.order.infraestructure.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDocument {
    private ProductDocument product;
    private int quantity;
    private String description;
}
