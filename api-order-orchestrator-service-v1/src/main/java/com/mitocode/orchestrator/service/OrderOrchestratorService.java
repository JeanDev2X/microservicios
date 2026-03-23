package com.mitocode.orchestrator.service;

import com.mitocode.orchestrator.controller.dto.CreateOrderOrchestratorRequest;
import com.mitocode.orchestrator.controller.dto.CreateOrderOrchestratorResponse;

public interface OrderOrchestratorService {

    CreateOrderOrchestratorResponse createOrder(CreateOrderOrchestratorRequest request);

}
