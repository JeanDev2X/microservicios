package com.mitocode.dto;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(Long id, LocalDateTime date, String status, List<OrderItemResponse> items) {
}
