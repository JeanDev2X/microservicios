package com.mitocode.order.listener.delivery.completed;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.order.infraestructure.document.OrderDocument;
import com.mitocode.order.infraestructure.repository.OrderRepository;
import com.mitocode.order.listener.delivery.completed.event.DeliveryCompletedEvent;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class DeliveryCompletedListener {

    private final OrderRepository orderRepository;
    private final ObjectMapper mapper;

    @KafkaListener(topics = "delivery.delivery-completed.v1", groupId = "delivery.delivery-completed.v1.group")
    public void handle(String message) {
        try {

            log.info("Updated Delivery Completed info from message: {}", message);
            DeliveryCompletedEvent event = mapper.readValue(message, DeliveryCompletedEvent.class);

            OrderDocument orderDocument = orderRepository.findById(event.getOrderId()).orElseThrow(() -> new NotFoundException("Order Not Found"));

            orderDocument.setDeliveryStatus(event.getStatus());

            orderRepository.save(orderDocument);
        } catch (Exception ex) {
            log.error("Error trying to process Delivery Completed Event", ex);
        }
    }
}
