package com.mitocode.restaurant.service;

import com.mitocode.restaurant.domain.Order;
import com.mitocode.restaurant.producer.restaurant.order.released.RestaurantOrderCancelledProducer;
import com.mitocode.restaurant.producer.restaurant.order.reserved.RestaurantOrderReservedProducer;
import com.mitocode.restaurant.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Slf4j
@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final RestaurantOrderReservedProducer producer;
    private final RestaurantOrderCancelledProducer orderCancelledProducer;

    public Order reserveOrder(Order order) {
        Order saved = repository.save(order);

        producer.produce(saved);

        return saved;
    }

    public void releaseOrder(Long restaurantId, UUID orderId) {
        log.info("Releasing Order {} for Restaurant {}", orderId, restaurantId);

        Order order = repository.getBy(restaurantId, orderId);

        order.setStatus("CANCELLED_BY_RESTAURANT");
        order.setReason("Some reason to cancel the order");

        repository.save(order);
        //hace algo para notificar al restaurante
        orderCancelledProducer.produce(order);
    }

}
