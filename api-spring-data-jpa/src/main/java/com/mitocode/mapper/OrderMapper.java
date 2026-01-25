package com.mitocode.mapper;

import com.mitocode.dto.CreateOrderRequest;
import com.mitocode.dto.OrderItemResponse;
import com.mitocode.dto.OrderResponse;
import com.mitocode.dto.ProductResponse;
import com.mitocode.entity.Order;
import com.mitocode.entity.OrderItem;
import com.mitocode.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderResponse toOrderResponse(Order order) {
        List<OrderItemResponse> itemResponses = order.getItems().stream()
                .map(OrderMapper::toOrderItemResponse)
                .collect(Collectors.toList());

        return new OrderResponse(
                order.getId(),
                order.getDate(),
                order.getStatus(),
                itemResponses
        );
    }

    public static OrderItemResponse toOrderItemResponse(OrderItem item) {
        ProductResponse productResponse = toProductResponse(item.getProduct());
        return new OrderItemResponse(item.getId(), productResponse, item.getQuantity());
    }

    public static ProductResponse toProductResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getPrice());
    }

}

