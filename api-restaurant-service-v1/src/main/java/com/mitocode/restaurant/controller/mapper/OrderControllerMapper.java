package com.mitocode.restaurant.controller.mapper;

import com.mitocode.restaurant.domain.Customer;
import com.mitocode.restaurant.domain.Order;
import com.mitocode.restaurant.domain.OrderItem;
import com.mitocode.restaurant.domain.Restaurant;
import com.mitocode.restaurant.dto.OrderItemResponse;
import com.mitocode.restaurant.dto.ReserveOrderRequest;
import com.mitocode.restaurant.dto.ReserveOrderResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderControllerMapper {

    public Order toDomain(ReserveOrderRequest request) {
        Restaurant restaurant = new Restaurant(request.restaurantId(), null);
        Customer customer = new Customer(request.customerId());
        List<OrderItem> items = request.items().stream()
                .map(i -> new OrderItem(i.productId(), i.productName(), i.quantity(), i.description()))
                .toList();
        return new Order(request.orderId(), restaurant, customer, items, null, null);
    }

    public ReserveOrderResponse toResponse(Order order) {
        List<OrderItemResponse> items = order.getItems().stream()
                .map(i -> new OrderItemResponse(i.getProductId(), i.getProductName(), i.getQuantity(), i.getDescription()))
                .toList();
        return new ReserveOrderResponse(order.getOrderId(), order.getRestaurant().getId(),
                order.getCustomer().getCustomerId(), items);
    }
}
