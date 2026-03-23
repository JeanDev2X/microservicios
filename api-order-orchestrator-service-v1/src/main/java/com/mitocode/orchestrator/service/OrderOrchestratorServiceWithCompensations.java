package com.mitocode.orchestrator.service;

import com.mitocode.orchestrator.client.orders.restclient.dto.CreateOrderResponse;
import com.mitocode.orchestrator.controller.dto.CreateOrderOrchestratorRequest;
import com.mitocode.orchestrator.controller.dto.CreateOrderOrchestratorResponse;
import com.mitocode.orchestrator.service.saga.CreateOrderSagaContext;
import com.mitocode.orchestrator.service.saga.SagaStep;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service("orchestratorWithCompensations")
@AllArgsConstructor
public class OrderOrchestratorServiceWithCompensations implements OrderOrchestratorService{

    private final List<SagaStep> steps;

    @Override
    public CreateOrderOrchestratorResponse createOrder(CreateOrderOrchestratorRequest request) {

        log.info("Starting order creation with compensations");
        CreateOrderSagaContext context = new CreateOrderSagaContext(request);

        for (SagaStep step : steps) {
            step.execute(context);
        }

        log.info("Order creation completed successfully with compensations");
        return new CreateOrderOrchestratorResponse(UUID.fromString(context.getOrderId()));

    }

}
