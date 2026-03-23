package com.mitocode.orchestrator.service.saga;

import com.mitocode.orchestrator.client.orders.restclient.dto.CreateOrderResponse;
import com.mitocode.orchestrator.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Order(100)
@AllArgsConstructor
public class CreateOrderStep implements SagaStep {

    private final OrderService orderService;

    @Override
    public void execute(CreateOrderSagaContext context) {
        log.info("Executing CreateOrderStep...");
        CreateOrderResponse orderCreated = orderService.createOrder(context.getRequest());
        context.setOrderId(orderCreated.id().toString());
        log.info("Order created successfully: {}", context.getOrderId());
    }

    @Override
    public void compensate(CreateOrderSagaContext context) {
        log.info("Compensating CreateOrderStep: cancelling order {}", context.getOrderId());
        orderService.cancelOrder(context.getOrderId(), "Order creation failed or rolled back");
    }
}
