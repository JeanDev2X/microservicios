package com.mitocode.payment.infraestructure.repository;

import com.mitocode.payment.infraestructure.entity.RefundEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefundRepositoryJPA extends JpaRepository<RefundEntity, Long> {
}
