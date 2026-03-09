package com.mitocode.orchestrator.client.delivery.restclient;

import com.mitocode.orchestrator.client.delivery.restclient.dto.AssignDriverRequest;
import com.mitocode.orchestrator.client.delivery.restclient.dto.DeliveryAddressRequest;
import com.mitocode.orchestrator.client.delivery.restclient.dto.DeliveryPersonRequest;
import com.mitocode.orchestrator.client.delivery.restclient.dto.DeliveryResponse;
import com.mitocode.orchestrator.controller.dto.CreateOrderOrchestratorRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class DeliveryServiceV1RestClient {

    private final RestClient deliveryRestClient;

    public DeliveryResponse assignDriver(UUID orderId, CreateOrderOrchestratorRequest createOrderRequest) {
        AssignDriverRequest assignDriverRequest = new AssignDriverRequest(
                orderId,
                new DeliveryAddressRequest(
                        createOrderRequest.deliveryAddress().address(),
                        createOrderRequest.deliveryAddress().latitude(),
                        createOrderRequest.deliveryAddress().longitude(),
                        createOrderRequest.deliveryAddress().reference()
                ),
                new DeliveryPersonRequest(
                        createOrderRequest.deliveryPerson().id()
                )
        );

        return deliveryRestClient.post()
                .uri("/assign-driver")
                .body(assignDriverRequest)
                .retrieve()
                .body(DeliveryResponse.class);
    }

    public void startDelivery(UUID orderId) {
        deliveryRestClient.post()
                .uri("/{orderId}/start", orderId)
                .retrieve()
                .toBodilessEntity();
    }

    public void completeDelivery(UUID orderId) {
        deliveryRestClient.post()
                .uri("/{orderId}/complete", orderId)
                .retrieve()
                .toBodilessEntity();
    }

}
