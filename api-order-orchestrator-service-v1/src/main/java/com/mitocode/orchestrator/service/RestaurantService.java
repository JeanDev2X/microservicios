package com.mitocode.orchestrator.service;

import com.mitocode.orchestrator.client.restaurants.RestaurantServiceV1WebClient;
import com.mitocode.orchestrator.controller.dto.CreateOrderOrchestratorRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantServiceV1WebClient client;

    public void reserveRestaurant(UUID orderId, CreateOrderOrchestratorRequest createOrderRequest) {
        log.info("Reserving restaurant for orderId: {}", orderId);
        client.reserverOrder(orderId, createOrderRequest);
    }

}
