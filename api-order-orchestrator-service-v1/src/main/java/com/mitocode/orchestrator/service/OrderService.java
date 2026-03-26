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

    public void cancelOrder(String orderId, String reason) {
        log.info("Cancelling order through Order Service V1 Client");
        orderServiceV1Client.cancelOrder(orderId, reason);
    }

    public void completeOrder(String orderId) {
        log.info("Completing order through Order Service V1 Client");
        orderServiceV1Client.updateOrderStatus(orderId, "COMPLETED");
    }

    public void updateStatus(String orderId, String status) {
        log.info("Updating order status through Order Service V1 Client");
        orderServiceV1Client.updateOrderStatus(orderId, status);
    }

}
