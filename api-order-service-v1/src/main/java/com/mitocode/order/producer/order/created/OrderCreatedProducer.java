package com.mitocode.order.producer.order.created;

import com.mitocode.order.domain.Order;
import com.mitocode.order.producer.order.created.event.Customer;
import com.mitocode.order.producer.order.created.event.OrderCreatedEvent;
import com.mitocode.order.producer.order.created.event.Restaurant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@Component
@AllArgsConstructor
public class OrderCreatedProducer {

    /*
     * Como conectarme a kafka desde el contenedor para ver la lista de topicos y para conectarme como productor y consumidor
     * Conectarse al contendor con: docker exec -it NOMBRE-CONTENEDOR-KAFKA bash
     * docker exec -it kafka bash
     *
     * Dentro del contendor ejecutamos el comando para listar los topicos:
     * kafka-topics --bootstrap-server localhost:9092 --list
     *
     * Dentro del contendor ejecutamos el comando para conectarnos como productores:
     * kafka-console-producer --bootstrap-server localhost:9092 --topic order.order-created.v1  // (order.order-created.v1) es el nombre del topico, puede ser cualquier otro
     *
     * Dentro del contendor ejecutamos el comando para conectarnos como consumidores:
     * kafka-console-consumer --bootstrap-server localhost:9092 --topic order.order-created.v1  // (order.order-created.v1) es el nombre del topico, puede ser cualquier otro
     *
     * Si queremos conectarnos como consumidores con un group-ip (para evitar que los mensajes se procesen mas de 1 vez) usamos el comando:
     * kafka-console-consumer --bootstrap-server localhost:9092 --topic order.order-created.v1 --group grupo-1 // (order.order-created.v1) es el nombre del topico, puede ser cualquier otro y (grupo-1) es el nombre del grupo, puede ser cualquier otro nombre
     * */


    private static final String TOPIC = "order.order-created.v1";

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper mapper;

    public void produce(Order order) {

        try {
            OrderCreatedEvent event = new OrderCreatedEvent(
                    order.getId(),
                    new Customer(order.getCustomer().getId(), order.getCustomer().getName()),
                    new Restaurant(order.getRestaurant().getId(), order.getRestaurant().getName()),
                    order.getTotal(),
                    order.getStatus().name()
            );
            kafkaTemplate.send(TOPIC, order.getId().toString(), mapper.writeValueAsString(event));
            log.info("Order Created Event send");
        } catch (Exception ex) {
            log.error("Error trying to send OrderCreated Event", ex);
        }

    }
}
