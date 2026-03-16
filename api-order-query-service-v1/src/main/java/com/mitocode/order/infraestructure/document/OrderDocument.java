package com.mitocode.order.infraestructure.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Data
@Document(collection = "orders")
public class OrderDocument {

    @Id
    private String id;

    private CustomerDocument customer;
    private RestaurantDocument restaurant;
    private PaymentDocument payment;
    private List<OrderItemDocument> items;
    private DeliveryAddressDocument deliveryAddress;
    private DeliveryPersonDocument deliveryPerson;
    private String deliveryStatus;
    private BigDecimal total;
    private String status;

}
