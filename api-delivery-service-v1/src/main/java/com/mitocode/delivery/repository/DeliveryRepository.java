package com.mitocode.delivery.repository;

import com.mitocode.delivery.domain.Delivery;
import com.mitocode.delivery.infraestructure.entity.DeliveryEntity;
import com.mitocode.delivery.infraestructure.repository.DeliveryRepositoryJpa;
import com.mitocode.delivery.repository.mapper.DeliveryMapper;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class DeliveryRepository {

    private final DeliveryRepositoryJpa deliveryRepository;
    private final EntityManager entityManager;

    public Optional<Delivery> findByIdWithDeliveryPerson(Long id) {
        return deliveryRepository.findByIdWithDeliveryPerson(id).map(DeliveryMapper::toDomain);
    }

    public Optional<Delivery> getByOrderId(UUID orderId) {
        return deliveryRepository.findByOrderId(orderId).map(DeliveryMapper::toDomain);
    }

    public Delivery save(Delivery delivery) {

        DeliveryEntity saved = deliveryRepository.save(DeliveryMapper.toEntity(delivery));

        // Refrescar para asegurarte que la relación está completamente cargada
        entityManager.flush();
        entityManager.refresh(saved);

        return DeliveryMapper.toDomain(saved);
    }

    public Delivery update(Delivery delivery) {

        DeliveryEntity updated = deliveryRepository.save(DeliveryMapper.toEntity(delivery));

        return DeliveryMapper.toDomain(updated);
    }
}
