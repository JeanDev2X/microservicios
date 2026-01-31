package com.mitocode.orchestrator.controller.dto;

import java.math.BigDecimal;

public record ProductRequest(Long id, String name, BigDecimal price) {
}
