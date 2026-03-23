package com.mitocode.restaurant.repository;

import com.mitocode.restaurant.domain.Order;
import com.mitocode.restaurant.infraestructure.entity.OrderEntity;
import com.mitocode.restaurant.infraestructure.repository.OrderJpaRepository;
import com.mitocode.restaurant.repository.mapper.OrderRepositoryMapper;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class OrderRepository {

    private final OrderJpaRepository jpaRepository;
    private final OrderRepositoryMapper mapper;

    public OrderRepository(OrderJpaRepository jpaRepository, OrderRepositoryMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    public Order getBy(Long restaurantId, UUID orderId) {
        OrderEntity entity = jpaRepository.findByRestaurantIdAndId(restaurantId, orderId);
        return mapper.toDomain(entity);
    }

    public Order save(Order order) {
        OrderEntity entity = mapper.toEntity(order);
        OrderEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }
}
