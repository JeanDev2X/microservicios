package com.mitocode.restaurant.infraestructure.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "order_items")
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private String productName;
    private int quantity;
    private String description;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

}