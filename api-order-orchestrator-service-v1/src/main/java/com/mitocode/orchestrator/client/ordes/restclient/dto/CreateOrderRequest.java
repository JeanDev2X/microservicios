package com.mitocode.orchestrator.client.ordes.restclient.dto;

import java.math.BigDecimal;

public record CreateOrderRequest(CustomerRequest customer,
                                 RestaurantRequest restaurant,
                                 BigDecimal total) {
}
