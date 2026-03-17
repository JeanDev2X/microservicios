package com.mitocode.order.listener.driver.assgined;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.order.infraestructure.document.DeliveryAddressDocument;
import com.mitocode.order.infraestructure.document.DeliveryPersonDocument;
import com.mitocode.order.infraestructure.document.OrderDocument;
import com.mitocode.order.infraestructure.repository.OrderRepository;
import com.mitocode.order.listener.driver.assgined.event.DriverAssignedEvent;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class DriverAssignedListener {

    private final OrderRepository orderRepository;
    private final ObjectMapper mapper;

    @KafkaListener(topics = "delivery.driver-assigned.v1", groupId = "delivery.driver-assigned.v1.group")
    public void handle(String message) {
        try {

            log.info("Updated Driver Assigned info from message: {}", message);
            DriverAssignedEvent event = mapper.readValue(message, DriverAssignedEvent.class);

            OrderDocument orderDocument = orderRepository.findById(event.getOrderId()).orElseThrow(() -> new NotFoundException("Order Not Found"));

            DeliveryAddressDocument deliveryAddress = new DeliveryAddressDocument();
            deliveryAddress.setAddress(event.getAddress());
            deliveryAddress.setLongitude(event.getLongitude());
            deliveryAddress.setLatitude(event.getLatitude());
            deliveryAddress.setReference(event.getReference());

            orderDocument.setDeliveryAddress(deliveryAddress);

            DeliveryPersonDocument deliveryPerson = new DeliveryPersonDocument();
            deliveryPerson.setId(event.getDeliveryPerson().getId());
            deliveryPerson.setName(event.getDeliveryPerson().getName());
            deliveryPerson.setPhoneNumber(event.getDeliveryPerson().getPhoneNumber());
            deliveryPerson.setVehicleType(event.getDeliveryPerson().getVehicleType());
            deliveryPerson.setLicensePlate(event.getDeliveryPerson().getLicensePlate());

            orderDocument.setDeliveryPerson(deliveryPerson);

            orderRepository.save(orderDocument);
        } catch (Exception ex) {
            log.error("Error trying to process Driver Assigned Event", ex);
        }
    }
}
