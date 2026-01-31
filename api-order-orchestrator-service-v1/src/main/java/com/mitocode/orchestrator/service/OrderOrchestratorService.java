package com.mitocode.orchestrator.service;

import com.mitocode.orchestrator.controller.dto.CreateOrderOrchestratorRequest;
import com.mitocode.orchestrator.controller.dto.CreateOrderOrchestratorResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderOrchestratorService {

    private final OrderService orderService;
    private final PaymentService paymentService;
    private final RestaurantService restaurantService;
    private final DeliveryService deliveryService;

    public CreateOrderOrchestratorResponse createOrder(CreateOrderOrchestratorRequest request) {
        return null;
    }

}
