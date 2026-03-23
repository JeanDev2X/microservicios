package com.mitocode.restaurant.infraestructure.repository;

import com.mitocode.restaurant.infraestructure.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, UUID> {
    OrderEntity findByRestaurantIdAndId(Long restaurantId, UUID orderId);
}
