package com.mitocode.order.listener.restaurant.order.released.event;

import lombok.Data;

@Data
public class RestaurantOrderReleasedEvent {
    String orderId;
    String status;
    String reason;
}
