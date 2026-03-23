package com.mitocode.orchestrator.controller;

import com.mitocode.orchestrator.controller.dto.CreateOrderOrchestratorRequest;
import com.mitocode.orchestrator.controller.dto.CreateOrderOrchestratorResponse;
import com.mitocode.orchestrator.service.DeliveryService;
import com.mitocode.orchestrator.service.OrderOrchestratorService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderOrchestratorController {

    private final OrderOrchestratorService service;
    private final DeliveryService deliveryService;

    public OrderOrchestratorController(
            @Qualifier("orchestratorWithCompensations")
            OrderOrchestratorService service,
            DeliveryService deliveryService) {
        this.service = service;
        this.deliveryService = deliveryService;
    }

    @PostMapping
    public ResponseEntity<CreateOrderOrchestratorResponse> createOrder(@RequestBody CreateOrderOrchestratorRequest request) {

        CreateOrderOrchestratorResponse createOrderResponse = service.createOrder(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(createOrderResponse);
    }

    @PostMapping("/{orderId}/delivery/start")
    public ResponseEntity<Void> startDelivery(@PathVariable UUID orderId) {

        deliveryService.startDelivery(orderId);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{orderId}/delivery/complete")
    public ResponseEntity<Void> completeDelivery(@PathVariable UUID orderId) {

        deliveryService.completeDelivery(orderId);

        return ResponseEntity.ok().build();
    }
}
