package com.mitocode.orchestrator.controller;

import com.mitocode.orchestrator.controller.dto.CreateOrderOrchestratorRequest;
import com.mitocode.orchestrator.controller.dto.CreateOrderOrchestratorResponse;
import com.mitocode.orchestrator.service.OrderOrchestratorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderOrchestratorController {

    private final OrderOrchestratorService service;

    @PostMapping
    public ResponseEntity<CreateOrderOrchestratorResponse> createOrder(@RequestBody CreateOrderOrchestratorRequest request) {

        CreateOrderOrchestratorResponse createOrderResponse = service.createOrder(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(createOrderResponse);
    }

    @PostMapping("/{orderId}/delivery/start")
    public ResponseEntity<Void> startDelivery(@PathVariable UUID orderId) {

        service.startDelivery(orderId);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{orderId}/delivery/complete")
    public ResponseEntity<Void> completeDelivery(@PathVariable UUID orderId) {

        service.completeDelivery(orderId);

        return ResponseEntity.ok().build();
    }
}
