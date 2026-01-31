package com.mitocode.payment.client.dto;

import java.math.BigDecimal;

public record CheckBalanceResponse(String accountId, BigDecimal balance, boolean sufficient) {
}
