package com.mitocode.orchestrator.client.ordes.restclient;

import com.mitocode.orchestrator.client.ordes.OrderServiceV1Client;
import com.mitocode.orchestrator.client.ordes.restclient.dto.*;
import com.mitocode.orchestrator.controller.dto.CreateOrderOrchestratorRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
@AllArgsConstructor
@Profile("RestClient")
public class OrderServiceV1RestClient implements OrderServiceV1Client {

    private final RestClient orderRestClient;

    public CreateOrderResponse createOrder(CreateOrderOrchestratorRequest createOrderOrchestratorRequest) {
        log.info("RestClient - Creating order for customer: {}", createOrderOrchestratorRequest.customer().name());
        CreateOrderRequest request = new CreateOrderRequest(
                new CustomerRequest(createOrderOrchestratorRequest.customer().id(), createOrderOrchestratorRequest.customer().name()),
                new RestaurantRequest(createOrderOrchestratorRequest.restaurant().id(), createOrderOrchestratorRequest.restaurant().name()),
                createOrderOrchestratorRequest.total());

        //return orderRestClient.post().uri("/orders") ya no se usa el .uri porque se configura en el bean RestClient desde config server

        return orderRestClient.post()
                .body(request)
                .retrieve()
                .body(CreateOrderResponse.class);

    }

    public void cancelOrder(String orderId, String reason) {
        log.info("RestClient - Cancelling order: {} for reason: {}", orderId, reason);
        orderRestClient.post()
                .uri("/{orderId}/cancel", orderId)
                .body(new CancelOrderRequest(reason))
                .retrieve()
                .body(Void.class);
    }

    public void updateOrderStatus(String orderId, String status) {
        log.info("RestClient - Updating order status: {} to {}", orderId, status);
        orderRestClient.put()
                .uri("/{orderId}/status", orderId)
                .body(new UpdateOrderStatusRequest(status))
                .retrieve()
                .body(Void.class);
    }

}