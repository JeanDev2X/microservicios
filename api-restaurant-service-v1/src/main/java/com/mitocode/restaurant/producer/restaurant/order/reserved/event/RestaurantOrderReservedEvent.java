package com.mitocode.restaurant.producer.restaurant.order.reserved.event;

import lombok.Data;

import java.util.List;

@Data
public class RestaurantOrderReservedEvent {
    String orderId;
    List<OrderItemEvent> items;
}
