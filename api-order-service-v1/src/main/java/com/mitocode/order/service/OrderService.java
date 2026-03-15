package com.mitocode.order.service;

import com.mitocode.order.domain.Order;
import com.mitocode.order.producer.order.created.OrderCreatedProducer;
import com.mitocode.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderCreatedProducer orderCreatedProducer;

    @Transactional
    public Order create(Order domain) {
        domain.setId(UUID.randomUUID());
        //guardar la orden en la base de datos, el estado de la orden se guarda como CREATED
        Order orderSaved = orderRepository.save(domain);
        //producir el evento de orden creada, para que el orquestador pueda consumirlo y continuar con el proceso de creación de la orden
        orderCreatedProducer.produce(orderSaved);

        return orderSaved;
    }

}
