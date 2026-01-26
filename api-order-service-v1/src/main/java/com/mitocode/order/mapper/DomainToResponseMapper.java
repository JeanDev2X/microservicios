package com.mitocode.order.mapper;

import com.mitocode.order.domain.Order;
import com.mitocode.order.dto.response.OrderResponse;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DomainToResponseMapper {

    public static OrderResponse toResponse(Order domain) {
        return new OrderResponse(
                domain.getId(),
                domain.getStatus()
        );
    }

}
