package com.mitocode.order.dto.request;

import java.math.BigDecimal;

public record CreateOrderRequest(CustomerRequest customer, RestaurantRequest restaurant, BigDecimal total) {
}
