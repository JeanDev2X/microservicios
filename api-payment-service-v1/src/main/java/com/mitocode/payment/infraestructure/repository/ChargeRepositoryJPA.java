package com.mitocode.payment.infraestructure.repository;

import com.mitocode.payment.infraestructure.entity.ChargeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargeRepositoryJPA extends JpaRepository<ChargeEntity, Long> {
}
