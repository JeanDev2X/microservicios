package com.mitocode.restaurant.producer.restaurant.order.released;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.restaurant.domain.Order;
import com.mitocode.restaurant.producer.restaurant.order.released.event.RestaurantOrderCancelledEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class RestaurantOrderCancelledProducer {

    private static final String TOPIC = "restaurant.restaurant-order-released.v1";
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper mapper;

    public void produce(Order order) {

        try {
            RestaurantOrderCancelledEvent event = new RestaurantOrderCancelledEvent();
            event.setOrderId(order.getOrderId().toString());
            event.setStatus(order.getStatus());
            event.setReason(order.getReason());

            kafkaTemplate.send(TOPIC, order.getOrderId().toString(), mapper.writeValueAsString(event));

            log.info("Restaurant Order Released Event send");
        } catch (Exception ex) {
            log.error("Error trying to send Restaurant Order Released Event", ex);
        }
    }
}
