package com.mitocode.order.domain;

public enum OrderStatus {
    CREATED,
    CONFIRMED,
    PREPARING,
    OUT_FOR_DELIVERY,
    DELIVERED,
    CANCELLED,
    PENDING_PAYMENT,
    COMPLETED
}