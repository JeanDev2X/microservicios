package com.mitocode.orchestrator.service.saga;

import com.mitocode.orchestrator.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Order(200)
@AllArgsConstructor
public class CheckBalanceStep implements SagaStep {

    private final PaymentService paymentService;

    @Override
    public void execute(CreateOrderSagaContext context) {
        log.info("Executing CheckBalanceStep...");

        boolean hasFunds = paymentService.checkBalance(
                context.getRequest().customer().id(),
                context.getRequest().card().id(),
                context.getRequest().total());

        if (!hasFunds) {
            log.info("Insufficient balance for order {}. Marking as PENDING_PAYMENT", context.getOrderId());
            context.setStatusPendingPayment(true);
            throw new RuntimeException("Insufficient balance for the order");
        }

        log.info("Balance check passed successfully");
    }

    @Override
    public void compensate(CreateOrderSagaContext context) {
        log.info("Compensating CheckBalanceStep: no compensation needed for balance check");
    }
}
