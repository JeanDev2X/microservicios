package com.mitocode.delivery.service;

import com.mitocode.delivery.domain.Delivery;
import com.mitocode.delivery.domain.DeliveryStatus;
import com.mitocode.delivery.producer.delivery.completed.DeliveryCompletedProducer;
import com.mitocode.delivery.producer.delivery.started.DeliveryStartedProducer;
import com.mitocode.delivery.producer.driver.assigned.DriverAssignedProducer;
import com.mitocode.delivery.producer.driver.released.DriverReleasedProducer;
import com.mitocode.delivery.repository.DeliveryPersonRepository;
import com.mitocode.delivery.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryPersonRepository deliveryPersonRepository;
    private final DriverAssignedProducer driverAssignedProducer;
    private final DeliveryStartedProducer deliveryStartedProducer;
    private final DeliveryCompletedProducer deliveryCompletedProducer;
    private final DriverReleasedProducer driverReleasedProducer;

    @Transactional
    public Delivery assignDriver(Delivery delivery) {
        deliveryPersonRepository.getById(delivery.getDeliveryPerson().getId())
                .orElseThrow(() -> new RuntimeException("Delivery person not found"));

        delivery.setStatus(DeliveryStatus.ASSIGNED);

        Delivery saved = deliveryRepository.save(delivery);
        log.info("kafka producer - driver assigned - delivery id");
        driverAssignedProducer.produce(saved);

        return saved;
        //return deliveryRepository.findByIdWithDeliveryPerson(saved.getId()).orElseThrow(() -> new RuntimeException("Error getting Delivery"));
    }

    @Transactional
    public Delivery releaseDriver(UUID orderId) {
        Delivery delivery = deliveryRepository.getByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));

        delivery.setStatus(DeliveryStatus.CANCELLED);

        Delivery saved = deliveryRepository.save(delivery);

        driverReleasedProducer.produce(saved);

        return saved;
        //return deliveryRepository.findByIdWithDeliveryPerson(saved.getId()).orElseThrow(() -> new RuntimeException("Error getting Delivery"));
    }

    @Transactional
    public Delivery startDelivery(UUID orderId) {
        log.info("Starting delivery for order {}", orderId);
        Delivery delivery = deliveryRepository.getByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));
        delivery.setStatus(DeliveryStatus.STARTED);
        Delivery updated = deliveryRepository.save(delivery);
        deliveryStartedProducer.produce(updated);
        return updated;
    }

    @Transactional
    public Delivery completeDelivery(UUID orderId) {
        Delivery delivery = deliveryRepository.getByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));
        delivery.setStatus(DeliveryStatus.COMPLETED);
        Delivery updated =  deliveryRepository.save(delivery);
        deliveryCompletedProducer.produce(updated);
        return updated;
    }

}
