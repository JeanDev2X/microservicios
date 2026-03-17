package com.mitocode.restaurant.service;

import com.mitocode.restaurant.domain.Order;
import com.mitocode.restaurant.producer.restaurant.order.reserved.RestaurantOrderReservedProducer;
import com.mitocode.restaurant.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final RestaurantOrderReservedProducer producer;

    public Order reserveOrder(Order order) {
        Order saved = repository.save(order);

        producer.produce(saved);

        return saved;
    }
}
