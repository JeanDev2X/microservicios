package com.mitocode.orchestrator.service.saga;

import com.mitocode.orchestrator.controller.dto.CreateOrderOrchestratorRequest;
import lombok.Data;

@Data
public class CreateOrderSagaContext {

    private String orderId;
    private CreateOrderOrchestratorRequest request;
    private boolean statusPendingPayment = false;

    public CreateOrderSagaContext(CreateOrderOrchestratorRequest request) {
        this.request = request;
    }
}
