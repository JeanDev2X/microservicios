package com.mitocode.delivery.producer.delivery.completed.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeliveryCompletedEvent {
    private String orderId;
    private String status;
}
