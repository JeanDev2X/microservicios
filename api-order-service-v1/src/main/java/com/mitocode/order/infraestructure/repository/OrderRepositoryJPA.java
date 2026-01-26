package com.mitocode.order.infraestructure.repository;

import com.mitocode.order.infraestructure.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface OrderRepositoryJPA extends JpaRepository<OrderEntity, UUID> {
}
