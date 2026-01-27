package com.mitocode.delivery.infraestructure.repository;

import com.mitocode.delivery.infraestructure.entity.DeliveryPersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryPersonRepositoryJpa extends JpaRepository<DeliveryPersonEntity, Long> {
}