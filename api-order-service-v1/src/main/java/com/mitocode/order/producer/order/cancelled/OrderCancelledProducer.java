package com.mitocode.order.producer.order.cancelled;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.order.domain.Order;
import com.mitocode.order.producer.order.cancelled.event.OrderCancelledEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class OrderCancelledProducer {

    private static final String TOPIC = "order.order-cancelled.v1";
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper mapper;

    public void produce(Order order) {

        try {
            OrderCancelledEvent event = new OrderCancelledEvent(
                    order.getId(),
                    order.getStatus().name(),
                    order.getCancelReason()
            );

            kafkaTemplate.send(TOPIC, order.getId().toString(), mapper.writeValueAsString(event));

            log.info("Order Cancelled Event send");
        } catch (Exception ex) {
            log.error("Error trying to send Order Cancelled Event", ex);
        }

    }
}
