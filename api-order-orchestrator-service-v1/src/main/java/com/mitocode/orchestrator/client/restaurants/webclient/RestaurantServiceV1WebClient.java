package com.mitocode.orchestrator.client.restaurants.webclient;

import com.mitocode.orchestrator.client.restaurants.dto.OrderItemRequest;
import com.mitocode.orchestrator.client.restaurants.dto.ReserveOrderRequest;
import com.mitocode.orchestrator.client.restaurants.dto.ReserveOrderResponse;
import com.mitocode.orchestrator.controller.dto.CreateOrderOrchestratorRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class RestaurantServiceV1WebClient {

    private final WebClient restaurantWebClient;

    public ReserveOrderResponse reserverOrder(UUID orderId, CreateOrderOrchestratorRequest orchestratorRequest) {
        log.info("WebClient - Reserving order at restaurant: {}", orchestratorRequest.restaurant().name());

        ReserveOrderRequest request = new ReserveOrderRequest(
                orderId,
                orchestratorRequest.restaurant().id(),
                orchestratorRequest.customer().id(),
                orchestratorRequest.items()
                        .stream()
                        .map(item -> new OrderItemRequest(
                                item.product().id(),
                                item.product().name(),
                                item.quantity(),
                                item.description()
                        ))
                        .toList()
        );

        return restaurantWebClient.post()
                .uri("/{restaurantId}/orders/reserve", orchestratorRequest.restaurant().id())
                //.header("Content-Type", "application/json")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ReserveOrderResponse.class)
                .block();
    }
}
