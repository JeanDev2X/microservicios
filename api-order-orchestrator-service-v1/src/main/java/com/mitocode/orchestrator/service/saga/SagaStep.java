package com.mitocode.orchestrator.service.saga;

public interface SagaStep {

    void execute(CreateOrderSagaContext context);

    void compensate(CreateOrderSagaContext context);
}
