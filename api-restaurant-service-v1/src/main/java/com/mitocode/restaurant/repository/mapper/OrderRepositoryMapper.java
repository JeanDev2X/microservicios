package com.mitocode.restaurant.repository.mapper;

import com.mitocode.restaurant.domain.Customer;
import com.mitocode.restaurant.domain.Order;
import com.mitocode.restaurant.domain.OrderItem;
import com.mitocode.restaurant.domain.Restaurant;
import com.mitocode.restaurant.infraestructure.entity.OrderEntity;
import com.mitocode.restaurant.infraestructure.entity.OrderItemEntity;
import com.mitocode.restaurant.infraestructure.entity.RestaurantEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderRepositoryMapper {

    public OrderEntity toEntity(Order order) {
        OrderEntity entity = new OrderEntity();
        entity.setId(order.getOrderId());
        entity.setRestaurant(new RestaurantEntity());
        entity.getRestaurant().setId(order.getRestaurant().getId());
        entity.setCustomerId(order.getCustomer().getCustomerId());

        List<OrderItemEntity> itemEntities = order.getItems().stream().map(i -> {
            OrderItemEntity e = new OrderItemEntity();
            e.setProductId(i.getProductId());
            e.setProductName(i.getProductName());
            e.setQuantity(i.getQuantity());
            e.setDescription(i.getDescription());
            e.setOrder(entity);
            return e;
        }).toList();

        entity.setItems(itemEntities);
        return entity;
    }

    public Order toDomain(OrderEntity entity) {
        Restaurant restaurant = new Restaurant(entity.getRestaurant().getId(), entity.getRestaurant().getName());
        Customer customer = new Customer(entity.getCustomerId());

        List<OrderItem> items = entity.getItems().stream()
                .map(e -> new OrderItem(e.getProductId(), e.getProductName(), e.getQuantity(), e.getDescription()))
                .toList();

        return new Order(entity.getId(), restaurant, customer, items, entity.getStatus(), entity.getReason());
    }

}
