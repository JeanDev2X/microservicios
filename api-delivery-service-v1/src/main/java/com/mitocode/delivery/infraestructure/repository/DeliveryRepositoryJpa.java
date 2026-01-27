package com.mitocode.delivery.infraestructure.repository;

import com.mitocode.delivery.infraestructure.entity.DeliveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface DeliveryRepositoryJpa extends JpaRepository<DeliveryEntity, Long> {

    Optional<DeliveryEntity> findByOrderId(UUID orderId);

    @Query("SELECT d FROM DeliveryEntity d JOIN FETCH d.deliveryPerson WHERE d.id = :id")
    Optional<DeliveryEntity> findByIdWithDeliveryPerson(@Param("id") Long id);
}
