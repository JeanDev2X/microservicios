package com.mitocode.order.service;

import com.mitocode.order.domain.Order;
import com.mitocode.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public Order create(Order domain) {
        domain.setId(UUID.randomUUID());
        return orderRepository.save(domain);
    }

}
