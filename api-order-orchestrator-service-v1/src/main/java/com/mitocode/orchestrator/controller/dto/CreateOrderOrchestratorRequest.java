package com.mitocode.orchestrator.controller.dto;

import java.math.BigDecimal;
import java.util.List;

public record CreateOrderOrchestratorRequest(
        CustomerRequest customer,
        CardRequest card,
        RestaurantRequest restaurant,
        List<OrderItemRequest> items,
        DeliveryAddress deliveryAddress,
        DeliveryPerson deliveryPerson,
        BigDecimal total
) {}
