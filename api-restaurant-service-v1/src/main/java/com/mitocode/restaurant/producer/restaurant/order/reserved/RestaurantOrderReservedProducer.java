package com.mitocode.restaurant.producer.restaurant.order.reserved;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.restaurant.domain.Order;
import com.mitocode.restaurant.producer.restaurant.order.reserved.event.OrderItemEvent;
import com.mitocode.restaurant.producer.restaurant.order.reserved.event.RestaurantOrderReservedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class RestaurantOrderReservedProducer {

    private static final String TOPIC = "restaurant.restaurant-order-reserved.v1";
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper mapper;

    public void produce(Order order) {

        try {
            RestaurantOrderReservedEvent event = new RestaurantOrderReservedEvent();
            event.setOrderId(order.getOrderId().toString());
            event.setItems(order.getItems()
                    .stream()
                    .map(item ->
                            new OrderItemEvent(item.getProductId(), item.getProductName(), item.getQuantity(), item.getDescription())
                    )
                    .toList()
            );

            kafkaTemplate.send(TOPIC, order.getOrderId().toString(), mapper.writeValueAsString(event));

            log.info("Restaurant Order Reserved Event send");
        } catch (Exception ex) {
            log.error("Error trying to send Restaurant Order Reserved Event", ex);
        }
    }
}
