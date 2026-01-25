package com.mitocode.repository;

import com.mitocode.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // Query Methods sección 2
    List<Order> findByStatus(String status);

    List<Order> findByDateBetween(LocalDateTime start, LocalDateTime end);

    // Nativa (sección 5): contar órdenes por status
    @Query(value = "SELECT COUNT(*) FROM orders o WHERE o.status = ?1", nativeQuery = true)
    long countByStatusNative(String status);
}