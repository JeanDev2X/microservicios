package com.mitocode.restaurant.producer.restaurant.order.released.event;

import lombok.Data;

@Data
public class RestaurantOrderCancelledEvent {
    String orderId;
    String status;
    String reason;
}
