package com.mitocode.orchestrator.service.saga;

import com.mitocode.orchestrator.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@Order(300)
@AllArgsConstructor
public class PaymentStep implements SagaStep {

    private final PaymentService paymentService;

    @Override
    public void execute(CreateOrderSagaContext context) {
        log.info("Executing PaymentStep for order {}", context.getOrderId());
        paymentService.charge(
                UUID.fromString(context.getOrderId()),
                context.getRequest().customer().id(),
                context.getRequest().card().id(),
                context.getRequest().total());
    }

    @Override
    public void compensate(CreateOrderSagaContext context) {
        log.info("Compensating PaymentStep: refunding payment for order {}", context.getOrderId());

        paymentService.refund(
                context.getOrderId(),
                context.getRequest().customer().id(),
                context.getRequest().card().id(),
                context.getRequest().total());
    }
}
