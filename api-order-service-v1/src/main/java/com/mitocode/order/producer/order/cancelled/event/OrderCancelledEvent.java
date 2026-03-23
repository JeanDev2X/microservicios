package com.mitocode.order.producer.order.cancelled.event;

import java.util.UUID;

public record OrderCancelledEvent(UUID id, String status, String reason) {
}
