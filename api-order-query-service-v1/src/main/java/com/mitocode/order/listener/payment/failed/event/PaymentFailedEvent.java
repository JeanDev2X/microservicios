package com.mitocode.order.listener.payment.failed.event;

import lombok.Data;

@Data
public class PaymentFailedEvent {
    private String orderId;
    private ChargeEvent charge;
    private String status;
    private String description;
}
