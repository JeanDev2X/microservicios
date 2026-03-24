package com.mitocode.delivery.producer.driver.released.event;

import lombok.Data;

@Data
public class DriverReleasedEvent {
    private String orderId;
    private String status;
}
