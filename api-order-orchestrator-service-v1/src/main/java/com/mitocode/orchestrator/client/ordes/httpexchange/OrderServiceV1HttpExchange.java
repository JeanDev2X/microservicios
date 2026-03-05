package com.mitocode.orchestrator.client.ordes.httpexchange;

import com.mitocode.orchestrator.client.ordes.OrderServiceV1Client;
import com.mitocode.orchestrator.client.ordes.restclient.dto.CreateOrderRequest;
import com.mitocode.orchestrator.client.ordes.restclient.dto.CreateOrderResponse;
import com.mitocode.orchestrator.client.ordes.restclient.dto.CustomerRequest;
import com.mitocode.orchestrator.client.ordes.restclient.dto.RestaurantRequest;
import com.mitocode.orchestrator.controller.dto.CreateOrderOrchestratorRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import static reactor.netty.http.HttpConnectionLiveness.log;

@Slf4j
//@Primary
@Component
@AllArgsConstructor
@Profile("HttpExchange")
public class OrderServiceV1HttpExchange implements OrderServiceV1Client {

    private final OrderServiceV1HttpExchangeClient orderClient;

    public CreateOrderResponse createOrder(CreateOrderOrchestratorRequest createOrderOrchestratorRequest) {

        log.info("HttpExchange - Creating order for customer: {}", createOrderOrchestratorRequest.customer().name());

        CreateOrderRequest request = new CreateOrderRequest(
                new CustomerRequest(createOrderOrchestratorRequest.customer().id(), createOrderOrchestratorRequest.customer().name()),
                new RestaurantRequest(createOrderOrchestratorRequest.restaurant().id(), createOrderOrchestratorRequest.restaurant().name()),
                createOrderOrchestratorRequest.total());

        //return orderRestClient.post().uri("/orders") ya no se usa el .uri porque se configura en el bean RestClient desde config server

        return orderClient.create(request);

    }

    @Override
    public void cancelOrder(String orderId, String reason) {

    }

    @Override
    public void updateOrderStatus(String orderId, String status) {

    }
}