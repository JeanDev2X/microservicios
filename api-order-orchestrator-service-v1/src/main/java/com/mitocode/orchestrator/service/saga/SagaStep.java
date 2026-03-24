package com.mitocode.orchestrator.service.saga;

public interface SagaStep {
    //cuando el proceso se ejecuta correctamente se llama a execute, si el proceso falla se llama a compensate para revertir los cambios realizados por execute
    void execute(CreateOrderSagaContext context);

    void compensate(CreateOrderSagaContext context);
}
