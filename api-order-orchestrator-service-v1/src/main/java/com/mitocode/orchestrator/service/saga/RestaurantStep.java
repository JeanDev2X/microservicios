package com.mitocode.orchestrator.service.saga;

import com.mitocode.orchestrator.service.RestaurantService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@Order(400)
@AllArgsConstructor
public class RestaurantStep implements SagaStep {

    private final RestaurantService restaurantService;

    @Override
    public void execute(CreateOrderSagaContext context) {
        log.info("Executing RestaurantStep for order {}", context.getOrderId());
        restaurantService.reserveRestaurant(
                UUID.fromString(context.getOrderId()),
                context.getRequest()
        );
    }

    @Override
    public void compensate(CreateOrderSagaContext context) {

    }
}
