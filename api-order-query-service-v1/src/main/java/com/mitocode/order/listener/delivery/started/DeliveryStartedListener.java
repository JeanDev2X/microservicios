package com.mitocode.order.listener.delivery.started;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.order.infraestructure.document.OrderDocument;
import com.mitocode.order.infraestructure.repository.OrderRepository;
import com.mitocode.order.listener.delivery.started.event.DeliveryStartedEvent;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class DeliveryStartedListener {

    private final OrderRepository orderRepository;
    private final ObjectMapper mapper;

    @KafkaListener(topics = "delivery.delivery-started.v1", groupId = "delivery.delivery-started.v1.group")
    public void handle(String message) {
        try {

            log.info("Updated Delivery Started info from message: {}", message);
            DeliveryStartedEvent event = mapper.readValue(message, DeliveryStartedEvent.class);

            OrderDocument orderDocument = orderRepository.findById(event.getOrderId()).orElseThrow(() -> new NotFoundException("Order Not Found"));

            orderDocument.setDeliveryStatus(event.getStatus());

            orderRepository.save(orderDocument);
        } catch (Exception ex) {
            log.error("Error trying to process Delivery Started Event", ex);
        }
    }
}
