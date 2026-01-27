package com.mitocode.delivery.service;

import com.mitocode.delivery.domain.Delivery;
import com.mitocode.delivery.domain.DeliveryStatus;
import com.mitocode.delivery.repository.DeliveryPersonRepository;
import com.mitocode.delivery.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryPersonRepository deliveryPersonRepository;

    @Transactional
    public Delivery assignDriver(Delivery delivery) {
        deliveryPersonRepository.getById(delivery.getDeliveryPerson().getId())
                .orElseThrow(() -> new RuntimeException("Delivery person not found"));

        delivery.setStatus(DeliveryStatus.ASSIGNED);

        return deliveryRepository.save(delivery);

        //return deliveryRepository.findByIdWithDeliveryPerson(saved.getId()).orElseThrow(() -> new RuntimeException("Error getting Delivery"));
    }

    @Transactional
    public Delivery startDelivery(UUID orderId) {
        Delivery delivery = deliveryRepository.getByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));
        delivery.setStatus(DeliveryStatus.STARTED);
        return deliveryRepository.save(delivery);
    }

    @Transactional
    public Delivery completeDelivery(UUID orderId) {
        Delivery delivery = deliveryRepository.getByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));
        delivery.setStatus(DeliveryStatus.COMPLETED);
        return deliveryRepository.save(delivery);
    }

}
