package com.mitocode.order.listener.order.cancelled.event;

public record OrderCancelledEvent(String id, String status, String reason) {
}
