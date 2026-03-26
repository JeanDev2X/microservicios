package com.mitocode.orchestrator.service.saga;

import com.mitocode.orchestrator.service.DeliveryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@Order(500)
@AllArgsConstructor
public class DeliveryStep implements SagaStep {

    private final DeliveryService deliveryService;

    @Override
    public void execute(CreateOrderSagaContext context) {
        log.info("Executing DeliveryStep for order {}", context.getOrderId());
        deliveryService.assignDriver(UUID.fromString(context.getOrderId()), context.getRequest());
    }

    @Override
    public void compensate(CreateOrderSagaContext context) {
        log.info("Compensating DeliveryStep: unassigning driver for order {}", context.getOrderId());
        deliveryService.releaseDriver(UUID.fromString(context.getOrderId()));
    }
}
