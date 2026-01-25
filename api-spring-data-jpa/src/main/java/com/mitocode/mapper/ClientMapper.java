package com.mitocode.mapper;

import com.mitocode.dto.*;
import com.mitocode.entity.Client;
import com.mitocode.entity.Order;
import com.mitocode.entity.OrderItem;
import com.mitocode.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ClientMapper {

    public static Client toClient(ClientRequest clientRequest) {
        Client client = new Client();
        client.setId(clientRequest.id());
        client.setName(clientRequest.name());
        client.setEmail(clientRequest.email());
        return client;
    }

    public static ClientResponse toClientResponse(Client client) {
        return new ClientResponse(
                client.getId(),
                client.getName(),
                client.getEmail(),
                client.getOrders() == null ? List.of() :
                        client.getOrders().stream()
                                .map(ClientMapper::toOrderResponse)
                                .collect(Collectors.toList())
        );
    }

    private static OrderResponse toOrderResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getDate(),
                order.getStatus(),
                order.getItems() == null ? List.of() :
                        order.getItems().stream()
                                .map(ClientMapper::toOrderItemResponse)
                                .collect(Collectors.toList())
        );
    }

    private static OrderItemResponse toOrderItemResponse(OrderItem item) {
        return new OrderItemResponse(
                item.getId(),
                toProductResponse(item.getProduct()),
                item.getQuantity()
        );
    }

    private static ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }
}
