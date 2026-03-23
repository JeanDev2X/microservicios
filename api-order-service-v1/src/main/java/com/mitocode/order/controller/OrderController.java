package com.mitocode.order.controller;

import com.mitocode.order.domain.Order;
import com.mitocode.order.dto.request.CancelOrderRequest;
import com.mitocode.order.dto.request.CreateOrderRequest;
import com.mitocode.order.dto.request.UpdateOrderStatusRequest;
import com.mitocode.order.dto.response.CancelOrderResponse;
import com.mitocode.order.dto.response.OrderResponse;
import com.mitocode.order.mapper.DomainToResponseMapper;
import com.mitocode.order.mapper.RequestToDomainMapper;
import com.mitocode.order.repository.OrderRepository;
import com.mitocode.order.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    // PUT /api/v1/orders/{orderId}/status
    @PutMapping("/{orderId}/status")
    public ResponseEntity<OrderResponse> updateStatus(
            @PathVariable UUID orderId,
            @RequestBody UpdateOrderStatusRequest request) {
        OrderResponse updated = orderService.updateOrderStatus(orderId, request);
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/{orderId}/cancel")
    public ResponseEntity<CancelOrderResponse> cancelOrder(
            @PathVariable UUID orderId,
            @RequestBody CancelOrderRequest request) {
        Order cancelledOrder = orderService.cancelOrder(orderId, request);

        CancelOrderResponse response = new CancelOrderResponse(cancelledOrder.getStatus(), cancelledOrder.getCancelReason());
        return ResponseEntity.ok(response);
    }
}
