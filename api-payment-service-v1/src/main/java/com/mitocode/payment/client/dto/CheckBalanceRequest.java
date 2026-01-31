package com.mitocode.payment.client.dto;

import java.math.BigDecimal;

public record CheckBalanceRequest(String accountId, BigDecimal requiredAmount) {
}
