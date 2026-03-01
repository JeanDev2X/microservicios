package com.mitocode.payment.infraestructure.repository;

import com.mitocode.payment.infraestructure.entity.CheckBalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CheckBalanceRepositoryJPA extends JpaRepository<CheckBalanceEntity, UUID> {
}
