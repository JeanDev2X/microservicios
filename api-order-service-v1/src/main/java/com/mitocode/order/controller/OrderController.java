package com.mitocode.order.controller;

import com.mitocode.order.domain.Order;
import com.mitocode.order.dto.request.CreateOrderRequest;
import com.mitocode.order.dto.response.OrderResponse;
import com.mitocode.order.mapper.DomainToResponseMapper;
import com.mitocode.order.mapper.RequestToDomainMapper;
import com.mitocode.order.repository.OrderRepository;
import com.mitocode.order.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
        //explicacion de mapeo, convertir request a domain video 4 > min 55
        Order order = RequestToDomainMapper.toDomain(request);
        Order createdOrder = orderService.create(order);
        return ResponseEntity.status(201).body(DomainToResponseMapper.toResponse(createdOrder));
    }

}
