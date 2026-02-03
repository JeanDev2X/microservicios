package com.mitocode.orchestrator.client.ordes.dto;

import java.math.BigDecimal;

public record CreateOrderRequest(CustomerRequest customer,
                                 RestaurantRequest restaurant,
                                 BigDecimal total) {
}
