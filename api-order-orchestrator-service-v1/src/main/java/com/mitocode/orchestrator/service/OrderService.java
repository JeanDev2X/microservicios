package com.mitocode.orchestrator.service;

import com.mitocode.orchestrator.client.orders.OrderServiceV1Client;
import com.mitocode.orchestrator.client.orders.restclient.dto.CreateOrderResponse;
import com.mitocode.orchestrator.controller.dto.CreateOrderOrchestratorRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class OrderService {

    private final OrderServiceV1Client orderServiceV1Client;

    public CreateOrderResponse createOrder(CreateOrderOrchestratorRequest orchestratorRequest) {
        log.info("Creating order through Order Service V1 Client");
        return orderServiceV1Client.createOrder(orchestratorRequest);
    }

}
