package com.mitocode.delivery.producer.driver.assigned;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.delivery.domain.Delivery;
import com.mitocode.delivery.producer.driver.assigned.event.DeliveryPersonEvent;
import com.mitocode.delivery.producer.driver.assigned.event.DriverAssignedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class DriverAssignedProducer {

    private static final String TOPIC = "delivery.driver-assigned.v1";
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper mapper;

    public void produce(Delivery delivery) {
        log.info("producing driver assigned delivery");
        try {
            DriverAssignedEvent event = new DriverAssignedEvent();
            event.setOrderId(delivery.getOrderId().toString());
            event.setAddress(delivery.getAddress());
            event.setLatitude(delivery.getLatitude());
            event.setLongitude(delivery.getLongitude());
            event.setReference(delivery.getReference());
            event.setStatus(delivery.getStatus().name());

            DeliveryPersonEvent person = new DeliveryPersonEvent();
            person.setId(delivery.getDeliveryPerson().getId());
            person.setName(delivery.getDeliveryPerson().getName());
            person.setPhoneNumber(delivery.getDeliveryPerson().getPhoneNumber());
            person.setVehicleType(delivery.getDeliveryPerson().getVehicleType());
            person.setLicensePlate(delivery.getDeliveryPerson().getLicensePlate());

            event.setDeliveryPerson(person);

            kafkaTemplate.send(TOPIC, delivery.getOrderId().toString(), mapper.writeValueAsString(event));

            log.info("Delivery Assigned Event sent");
        } catch (Exception ex) {
            log.error("Error trying to send Delivery Assigned Event", ex);
        }

    }
}
