package com.mitocode.orchestrator.client.orders.restclient.dto;

import java.math.BigDecimal;

public record CreateOrderRequest(CustomerRequest customer,
                                 RestaurantRequest restaurant,
                                 BigDecimal total) {
}
