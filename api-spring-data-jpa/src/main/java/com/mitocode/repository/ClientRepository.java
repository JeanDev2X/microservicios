package com.mitocode.repository;

import com.mitocode.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    // Query Methods (para sección 2)
    List<Client> findByNameContainingIgnoreCase(String name);

    long countByEmail(String email);

    // JPQL para sección 5 (clientes con más de N órdenes)
    @Query("SELECT c FROM Client c WHERE size(c.orders) > :minOrders")
    List<Client> findClientsWithMoreThanOrders(@Param("minOrders") int minOrders);

    Optional<Client> findByEmail(String email);
}
