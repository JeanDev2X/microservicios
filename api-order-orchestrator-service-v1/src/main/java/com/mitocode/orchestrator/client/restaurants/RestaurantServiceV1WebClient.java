package com.mitocode.orchestrator.client.restaurants;

import com.mitocode.orchestrator.client.restaurants.dto.OrderItemRequest;
import com.mitocode.orchestrator.client.restaurants.dto.ReserveOrderRequest;
import com.mitocode.orchestrator.client.restaurants.dto.ReserveOrderResponse;
import com.mitocode.orchestrator.controller.dto.CreateOrderOrchestratorRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

import static reactor.netty.http.HttpConnectionLiveness.log;

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
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ReserveOrderResponse.class)
                .block();
    }
}
