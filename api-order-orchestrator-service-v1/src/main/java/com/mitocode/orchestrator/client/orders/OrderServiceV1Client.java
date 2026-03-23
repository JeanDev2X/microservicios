package com.mitocode.orchestrator.client.orders;

import com.mitocode.orchestrator.client.orders.restclient.dto.CreateOrderResponse;
import com.mitocode.orchestrator.controller.dto.CreateOrderOrchestratorRequest;

public interface OrderServiceV1Client {

    CreateOrderResponse createOrder(CreateOrderOrchestratorRequest createOrderOrchestratorRequest);

}
