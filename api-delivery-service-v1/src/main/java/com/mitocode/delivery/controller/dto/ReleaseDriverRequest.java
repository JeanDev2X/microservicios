package com.mitocode.delivery.controller.dto;

import java.util.UUID;

public record ReleaseDriverRequest(
        UUID orderId
) {
}
