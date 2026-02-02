package com.mitocode.orchestrator.client.ordes;

import com.mitocode.orchestrator.client.ordes.dto.CreateOrderRequest;
import com.mitocode.orchestrator.client.ordes.dto.CreateOrderResponse;
import com.mitocode.orchestrator.client.ordes.dto.CustomerRequest;
import com.mitocode.orchestrator.client.ordes.dto.RestaurantRequest;
import com.mitocode.orchestrator.controller.dto.CreateOrderOrchestratorRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@AllArgsConstructor
public class OrderServiceV1Client {

    private final RestClient orderRestClient;

    public CreateOrderResponse createOrder(CreateOrderOrchestratorRequest createOrderOrchestratorRequest) {
        CreateOrderRequest request = new CreateOrderRequest(
                new CustomerRequest(createOrderOrchestratorRequest.customer().id(), createOrderOrchestratorRequest.customer().name()),
                new RestaurantRequest(createOrderOrchestratorRequest.restaurant().id(), createOrderOrchestratorRequest.restaurant().name()),
                createOrderOrchestratorRequest.total());

        //return orderRestClient.post().uri("/orders") ya no se usa el .uri porque se configura en el bean RestClient desde config server

        return orderRestClient.post().uri("/orders")
                .body(request)
                .retrieve()
                .body(CreateOrderResponse.class);

    }
}