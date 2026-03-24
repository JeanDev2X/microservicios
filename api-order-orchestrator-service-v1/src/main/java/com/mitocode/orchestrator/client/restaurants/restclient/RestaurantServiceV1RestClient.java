package com.mitocode.orchestrator.client.restaurants.restclient;

import com.mitocode.orchestrator.client.restaurants.dto.OrderItemRequest;
import com.mitocode.orchestrator.client.restaurants.dto.ReleaseOrderRequest;
import com.mitocode.orchestrator.client.restaurants.dto.ReserveOrderRequest;
import com.mitocode.orchestrator.client.restaurants.dto.ReserveOrderResponse;
import com.mitocode.orchestrator.controller.dto.CreateOrderOrchestratorRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Component
@AllArgsConstructor
public class RestaurantServiceV1RestClient {

    private final RestClient restaurantV1RestClient;

    public ReserveOrderResponse reserverOrder(UUID orderId, CreateOrderOrchestratorRequest orchestratorRequest) {

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

        return restaurantV1RestClient.post()
                .uri("/{restaurantId}/orders/reserve", orchestratorRequest.restaurant().id())
                //.header("Content-Type", "application/json")
                .body(request)
                .retrieve()
                .body(ReserveOrderResponse.class);
    }

    public ResponseEntity<Void> releaseOrder(UUID orderId, Long restaurantId) {
        ReleaseOrderRequest releaseOrderRequest = new ReleaseOrderRequest(orderId);

        return restaurantV1RestClient.post()
                .uri("/{restaurantId}/orders/release", restaurantId)
                .body(releaseOrderRequest)
                .retrieve()
                .toBodilessEntity();
    }

}
