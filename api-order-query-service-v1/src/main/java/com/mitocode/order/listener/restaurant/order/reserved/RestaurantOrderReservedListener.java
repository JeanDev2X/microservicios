package com.mitocode.order.listener.restaurant.order.reserved;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.order.infraestructure.document.OrderDocument;
import com.mitocode.order.infraestructure.document.OrderItemDocument;
import com.mitocode.order.infraestructure.document.ProductDocument;
import com.mitocode.order.infraestructure.repository.OrderRepository;
import com.mitocode.order.listener.restaurant.order.reserved.event.RestaurantOrderReservedEvent;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class RestaurantOrderReservedListener {

    private final OrderRepository orderRepository;
    private final ObjectMapper mapper;

    @KafkaListener(topics = "restaurant.restaurant-order-reserved.v1", groupId = "restaurant.restaurant-order-reserved.v1.group")
    public void handle(String message) {
        try {
            log.info("Updating Restaurant Order Reserved info from message: {}", message);
            RestaurantOrderReservedEvent event = mapper.readValue(message, RestaurantOrderReservedEvent.class);

            OrderDocument orderDocument = orderRepository.findById(event.getOrderId()).orElseThrow(() -> new NotFoundException("Order Not Found"));
            orderDocument.setItems(
                    event.getItems().stream().map(
                            item -> new OrderItemDocument(
                                    new ProductDocument(item.getProductId(), item.getProductName()),
                                    item.getQuantity(),
                                    item.getDescription()
                            )
                    ).toList()
            );

            orderRepository.save(orderDocument);
        } catch (Exception ex) {
            log.error("Error trying to process Order Created Event", ex);
        }
    }
}

