package com.mitocode.restaurant.controller;

import com.mitocode.restaurant.controller.mapper.OrderControllerMapper;
import com.mitocode.restaurant.domain.Order;
import com.mitocode.restaurant.dto.ReleaseOrderRequest;
import com.mitocode.restaurant.dto.ReserveOrderRequest;
import com.mitocode.restaurant.dto.ReserveOrderResponse;
import com.mitocode.restaurant.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurants")
public class OrderController {

    private final OrderService service;
    private final OrderControllerMapper mapper;

    public OrderController(OrderService service, OrderControllerMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping("/{restaurantId}/orders/reserve")
    public ResponseEntity<ReserveOrderResponse> reserveOrder(
            @PathVariable Long restaurantId,
            @RequestBody ReserveOrderRequest request) {

        ReserveOrderRequest updatedRequest = new ReserveOrderRequest(
                request.orderId(),
                restaurantId,
                request.customerId(),
                request.items()
        );

        Order order = mapper.toDomain(updatedRequest);

        Order reservedOrder = service.reserveOrder(order);
        return ResponseEntity.ok(mapper.toResponse(reservedOrder));
    }

    @PostMapping("/{restaurantId}/orders/release")
    public ResponseEntity<ReserveOrderResponse> releaseOrder(
            @PathVariable Long restaurantId,
            @RequestBody ReleaseOrderRequest request) {

        service.releaseOrder(restaurantId, request.orderId());

        return ResponseEntity.ok().build();
    }

}

