package com.mitocode.orchestrator.client.ordes;

import com.mitocode.orchestrator.client.ordes.restclient.dto.CreateOrderResponse;
import com.mitocode.orchestrator.controller.dto.CreateOrderOrchestratorRequest;

public interface OrderServiceV1Client {

    CreateOrderResponse createOrder(CreateOrderOrchestratorRequest createOrderOrchestratorRequest);

}
