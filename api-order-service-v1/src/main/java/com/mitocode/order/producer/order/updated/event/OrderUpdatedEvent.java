package com.mitocode.order.producer.order.updated.event;

import java.util.UUID;

public record OrderUpdatedEvent(UUID id, String status) {
}
