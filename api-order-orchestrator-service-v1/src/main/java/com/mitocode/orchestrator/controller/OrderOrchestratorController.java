package com.mitocode.orchestrator.controller;

import com.mitocode.orchestrator.controller.dto.CreateOrderOrchestratorRequest;
import com.mitocode.orchestrator.controller.dto.CreateOrderOrchestratorResponse;
import com.mitocode.orchestrator.service.OrderOrchestratorService;
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
public class OrderOrchestratorController {

    private final OrderOrchestratorService service;

    @PostMapping
    public ResponseEntity<CreateOrderOrchestratorResponse> createOrder(@RequestBody CreateOrderOrchestratorRequest request) {

        CreateOrderOrchestratorResponse createOrderResponse = service.createOrder(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(createOrderResponse);
    }

}
