package com.mitocode.order.listener.order.updated;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.order.infraestructure.document.OrderDocument;
import com.mitocode.order.infraestructure.repository.OrderRepository;
import com.mitocode.order.listener.order.updated.event.OrderUpdatedEvent;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class OrderUpdatedListener {

    private final OrderRepository orderRepository;
    private final ObjectMapper mapper;

    @KafkaListener(topics = "order.order-updated.v1", groupId = "order.order-updated.v1.group")
    public void handle(String message)  {
        try {
            log.info("Updating order from message: {}", message);
            OrderUpdatedEvent updatedEvent = mapper.readValue(message, OrderUpdatedEvent.class);

            OrderDocument  orderDocument = orderRepository.findById(updatedEvent.id()).orElseThrow(() -> new NotFoundException("Order Not Found"));
            orderDocument.setStatus(updatedEvent.status());
            orderRepository.save(orderDocument);
        } catch (Exception ex) {
            log.error("Error trying to process Order Updated Event", ex);
        }
    }
}
