package com.mitocode.order.producer.order.updated;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.order.domain.Order;
import com.mitocode.order.producer.order.updated.event.OrderUpdatedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class OrderUpdatedProducer {

    private static final String TOPIC = "order.order-updated.v1";
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper mapper;

    public void produce(Order order) {

        try {
            OrderUpdatedEvent event = new OrderUpdatedEvent(
                    order.getId(),
                    order.getStatus().name()
            );

            kafkaTemplate.send(TOPIC, order.getId().toString(), mapper.writeValueAsString(event));

            log.info("Order Updated Event send");
        } catch (Exception ex) {
            log.error("Error trying to send Order Updated Event", ex);
        }

    }
}
