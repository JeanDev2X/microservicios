package com.mitocode.order.listener.delivery.started.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeliveryStartedEvent {
    private String orderId;
    private String status;
}
