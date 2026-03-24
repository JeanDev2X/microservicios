package com.mitocode.order.listener.payment.refund.event;

import lombok.Data;

@Data
public class RefundCompletedEvent {
    private String orderId;
    private RefundEvent refund;
    private String status;
}
