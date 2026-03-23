package com.mitocode.payment.producer.refund.completed.event;

import lombok.Data;

@Data
public class RefundCompletedEvent {
    private String orderId;
    private RefundEvent refund;
    private String status;
}
