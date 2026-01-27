package com.mitocode.delivery.repository;

import com.mitocode.delivery.domain.DeliveryPerson;
import com.mitocode.delivery.infraestructure.repository.DeliveryPersonRepositoryJpa;
import com.mitocode.delivery.repository.mapper.DeliveryPersonMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class DeliveryPersonRepository {

    private final DeliveryPersonRepositoryJpa deliveryPersonRepository;

    public Optional<DeliveryPerson> getById(Long id) {
        return deliveryPersonRepository.findById(id).map(DeliveryPersonMapper::toDomain);
    }

}
