package com.mitocode.restaurant.service;

import com.mitocode.restaurant.domain.Order;
import com.mitocode.restaurant.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order reserveOrder(Order order) {
        return repository.save(order);
    }
}
