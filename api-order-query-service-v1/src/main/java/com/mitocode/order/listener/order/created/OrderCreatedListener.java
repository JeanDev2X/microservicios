package com.mitocode.order.listener.order.created;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.order.infraestructure.document.CustomerDocument;
import com.mitocode.order.infraestructure.document.OrderDocument;
import com.mitocode.order.infraestructure.document.RestaurantDocument;
import com.mitocode.order.infraestructure.repository.OrderRepository;
import com.mitocode.order.listener.order.created.event.OrderCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class OrderCreatedListener {

    private final OrderRepository repository;
    private final ObjectMapper mapper;

    //Importante agregar el groupId para que el mensaje sea procesado una sola vez por todas las instancias de este servicio
    @KafkaListener(topics = "order.order-created.v1", groupId = "order.order-created.v1.group")
    public void handle(String message) {
        try {
            log.info("Creating order from message: {}", message);
            //Processing logic would go here

            OrderCreatedEvent createdEvent = mapper.readValue(message, OrderCreatedEvent.class);

            OrderDocument orderDocument = new OrderDocument();
            orderDocument.setId(createdEvent.id().toString());

            CustomerDocument customerDocument = new CustomerDocument();
            customerDocument.setId(createdEvent.customer().id());
            customerDocument.setName(createdEvent.customer().name());
            orderDocument.setCustomer(customerDocument);

            RestaurantDocument restaurantDocument = new RestaurantDocument();
            restaurantDocument.setId(createdEvent.restaurant().id());
            restaurantDocument.setName(createdEvent.restaurant().name());
            orderDocument.setRestaurant(restaurantDocument);
            orderDocument.setTotal(createdEvent.total());
            orderDocument.setStatus(createdEvent.status());

            repository.save(orderDocument);

        } catch (Exception ex) {
            log.error("Error trying to process Order Created Event", ex);
        }
    }

}
