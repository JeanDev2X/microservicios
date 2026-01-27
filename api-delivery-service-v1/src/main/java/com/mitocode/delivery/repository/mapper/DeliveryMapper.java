package com.mitocode.delivery.repository.mapper;

import com.mitocode.delivery.domain.Delivery;
import com.mitocode.delivery.infraestructure.entity.DeliveryEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DeliveryMapper {

    public static Delivery toDomain(DeliveryEntity entity) {
        Delivery domain = new Delivery();
        domain.setId(entity.getId());
        domain.setOrderId(entity.getOrderId());
        domain.setAddress(entity.getAddress());
        domain.setLatitude(entity.getLatitude());
        domain.setLongitude(entity.getLongitude());
        domain.setReference(entity.getReference());
        domain.setStatus(entity.getStatus());
        domain.setDeliveryPerson(DeliveryPersonMapper.toDomain(entity.getDeliveryPerson()));
        return domain;
    }

    public static DeliveryEntity toEntity(Delivery delivery) {
        DeliveryEntity entity = new DeliveryEntity();
        entity.setId(delivery.getId());
        entity.setOrderId(delivery.getOrderId());
        entity.setAddress(delivery.getAddress());
        entity.setLatitude(delivery.getLatitude());
        entity.setLongitude(delivery.getLongitude());
        entity.setReference(delivery.getReference());
        entity.setDeliveryPerson(DeliveryPersonMapper.toEntity(delivery.getDeliveryPerson()));
        entity.setStatus(delivery.getStatus());

        return entity;
    }
}
