package com.mitocode.dto;

import java.util.List;

public record ClientResponse(Long id, String name, String email, List<OrderResponse> orders) {

}
