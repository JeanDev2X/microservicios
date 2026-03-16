package com.mitocode.payment.producer.payment.completed.event;

import lombok.Data;

@Data
public class PaymentCompletedEvent {
    private String orderId;
    private ChargeEvent charge;
}
