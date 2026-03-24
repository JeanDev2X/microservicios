package com.mitocode.order.listener.restaurant.order.released;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.order.infraestructure.document.OrderDocument;
import com.mitocode.order.infraestructure.repository.OrderRepository;
import com.mitocode.order.listener.restaurant.order.released.event.RestaurantOrderReleasedEvent;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class RestaurantOrderReleasedListener {

    private final OrderRepository orderRepository;
    private final ObjectMapper mapper;

    @KafkaListener(topics = "restaurant.restaurant-order-released.v1", groupId = "restaurant.restaurant-order-released.v1.group")
    public void handle(String message) {
        try {
            log.info("Updating Restaurant Order Released info from message: {}", message);
            RestaurantOrderReleasedEvent event = mapper.readValue(message, RestaurantOrderReleasedEvent.class);

            OrderDocument orderDocument = orderRepository.findById(event.getOrderId()).orElseThrow(() -> new NotFoundException("Order Not Found"));
            orderDocument.getRestaurant().setOrderStatus(event.getStatus());
            orderDocument.getRestaurant().setReason(event.getReason());

            orderRepository.save(orderDocument);
        } catch (Exception ex) {
            log.error("Error trying to process Restaurant Order Released Event", ex);
        }
    }
}

