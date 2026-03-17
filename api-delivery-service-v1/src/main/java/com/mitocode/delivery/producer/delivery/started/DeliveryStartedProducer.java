package com.mitocode.delivery.producer.delivery.started;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.delivery.domain.Delivery;
import com.mitocode.delivery.producer.delivery.started.event.DeliveryStartedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class DeliveryStartedProducer {

    private static final String TOPIC = "delivery.delivery-started.v1";
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper mapper;

    public void produce(Delivery delivery) {

        try {
            log.info("Delivery Started Event sent");
            DeliveryStartedEvent event = new DeliveryStartedEvent(delivery.getOrderId().toString(), delivery.getStatus().name());

            kafkaTemplate.send(TOPIC, delivery.getOrderId().toString(), mapper.writeValueAsString(event));

            log.info("Delivery Started Event sent");
        } catch (Exception ex) {
            log.error("Error trying to send Delivery Started Event", ex);
        }

    }
}
