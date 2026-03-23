package com.mitocode.payment.producer.payment.failed.event;

import lombok.Data;

@Data
public class PaymentFailedEvent {
    private String orderId;
    private ChargeEvent charge;
    private String status;
    private String description;
}
