package com.mitocode.order.listener.order.cancelled;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.order.infraestructure.document.OrderDocument;
import com.mitocode.order.infraestructure.repository.OrderRepository;
import com.mitocode.order.listener.order.cancelled.event.OrderCancelledEvent;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class OrderCancelledListener {

    private final OrderRepository orderRepository;
    private final ObjectMapper mapper;

    @KafkaListener(topics = "order.order-cancelled.v1", groupId = "order.order-cancelled.v1.group")
    public void handle(String message)  {
        try {
            log.info("Cancelling order from message: {}", message);
            OrderCancelledEvent event = mapper.readValue(message, OrderCancelledEvent.class);

            OrderDocument orderDocument = orderRepository.findById(event.id()).orElseThrow(() -> new NotFoundException("Order Not Found"));
            orderDocument.setStatus(event.status());
            orderDocument.setCancelReason(event.reason());
            orderRepository.save(orderDocument);
        } catch (Exception ex) {
            log.error("Error trying to process Order Cancelled Event", ex);
        }
    }
}
