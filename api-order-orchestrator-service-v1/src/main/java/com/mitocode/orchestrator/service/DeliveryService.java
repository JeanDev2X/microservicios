package com.mitocode.orchestrator.service;

import com.mitocode.orchestrator.client.delivery.restclient.DeliveryServiceV1RestClient;
import com.mitocode.orchestrator.client.delivery.restclient.dto.DeliveryResponse;
import com.mitocode.orchestrator.controller.dto.CreateOrderOrchestratorRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class DeliveryService {

    private final DeliveryServiceV1RestClient client;

    public DeliveryResponse assignDriver(UUID orderId, CreateOrderOrchestratorRequest createOrderRequest) {
        log.info("Assigning driver for order ID: {}", orderId);
        return client.assignDriver(orderId, createOrderRequest);
    }

    public ResponseEntity<Void> releaseDriver(UUID orderId) {
        return client.releaseDriver(orderId);
    }

    public void startDelivery(UUID orderId) {
        client.startDelivery(orderId);
    }

    public void completeDelivery(UUID orderId) {
        client.completeDelivery(orderId);
    }

}
