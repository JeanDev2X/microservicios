package com.mitocode.orchestrator.service;

import com.mitocode.orchestrator.client.ordes.OrderServiceV1Client;
import com.mitocode.orchestrator.client.ordes.dto.CreateOrderResponse;
import com.mitocode.orchestrator.controller.dto.CreateOrderOrchestratorRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class OrderService {

    private final OrderServiceV1Client orderRestClient;

    public CreateOrderResponse createOrder(CreateOrderOrchestratorRequest orchestratorRequest) {
        return orderRestClient.createOrder(orchestratorRequest);
    }

}
