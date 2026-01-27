package com.mitocode.restaurant.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    private UUID id;

    @ManyToOne
    private RestaurantEntity restaurant;

    private Long customerId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemEntity> items;

    private String status;

}
