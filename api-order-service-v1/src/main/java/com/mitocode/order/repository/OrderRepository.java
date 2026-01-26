package com.mitocode.order.repository;

import com.mitocode.order.domain.Order;
import com.mitocode.order.infraestructure.entity.OrderEntity;
import com.mitocode.order.infraestructure.repository.OrderRepositoryJPA;
import com.mitocode.order.mapper.DomainToEntityMapper;
import com.mitocode.order.mapper.EntityToDomainMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class OrderRepository {

    private final OrderRepositoryJPA orderRepository;

    public Optional<Order> findById(UUID id) {
        return orderRepository.findById(id)
                .map(EntityToDomainMapper::toDomain);
    }

    public Order save(Order order) {
        // Implementation to save Order, objetive is to persist the Order entity
        //Pasar de un objeto de dominio a un objeto de infraestructura(objecto de negocio a objeto de base de datos)
        OrderEntity entity = DomainToEntityMapper.toEntity(order);
        entity = orderRepository.save(entity);
        //pasar de un objeto de infraestructura a un objeto de dominio (objeto de base de datos a objeto de negocio)
        return EntityToDomainMapper.toDomain(entity);

    }

}
