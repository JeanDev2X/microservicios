package com.mitocode.delivery.producer.driver.released;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.delivery.domain.Delivery;
import com.mitocode.delivery.producer.driver.released.event.DriverReleasedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class DriverReleasedProducer {

    private static final String TOPIC = "delivery.driver-released.v1";
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper mapper;

    public void produce(Delivery delivery) {

        try {
            DriverReleasedEvent event = new DriverReleasedEvent();
            event.setOrderId(delivery.getOrderId().toString());
            event.setStatus(delivery.getStatus().name());

            kafkaTemplate.send(TOPIC, delivery.getOrderId().toString(), mapper.writeValueAsString(event));

            log.info("Driver Released Event sent");
        } catch (Exception ex) {
            log.error("Error trying to send Driver Released Event", ex);
        }

    }
}
