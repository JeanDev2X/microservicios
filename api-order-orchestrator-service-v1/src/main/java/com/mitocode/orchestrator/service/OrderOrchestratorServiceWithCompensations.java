package com.mitocode.orchestrator.service;

import com.mitocode.orchestrator.client.orders.restclient.dto.CreateOrderResponse;
import com.mitocode.orchestrator.controller.dto.CreateOrderOrchestratorRequest;
import com.mitocode.orchestrator.controller.dto.CreateOrderOrchestratorResponse;
import com.mitocode.orchestrator.excepcion.StopSagaException;
import com.mitocode.orchestrator.service.saga.CreateOrderSagaContext;
import com.mitocode.orchestrator.service.saga.SagaStep;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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
        //list to keep track of executed steps for compensation in case of failure
        List<SagaStep> executedSteps = new ArrayList<>();

        try {
            for (SagaStep step : steps) {
                step.execute(context);
                executedSteps.add(step);
            }
            log.info("Order creation completed successfully with compensations");
            return new CreateOrderOrchestratorResponse(UUID.fromString(context.getOrderId()));
        }catch (Exception ex) {

            //reverse the list to compensate in the correct order
            Collections.reverse(executedSteps);

            for (SagaStep step : executedSteps) {
                try {
                    step.compensate(context);
                } catch (Exception compEx) {
                    log.error("Compensation failed in step {}: {}", step.getClass().getSimpleName(), compEx.getMessage());
                    //Guardar en una tabla de compensaciones fallidas para reintentos manuales
                }
            }

            throw new StopSagaException("Order creation failed, triggering compensations: " + ex.getMessage(), ex);
        }

    }

}
