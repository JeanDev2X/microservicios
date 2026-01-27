package com.mitocode.delivery.repository.mapper;

import com.mitocode.delivery.domain.DeliveryPerson;
import com.mitocode.delivery.infraestructure.entity.DeliveryPersonEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DeliveryPersonMapper {

    public static DeliveryPerson toDomain(DeliveryPersonEntity entity) {
        DeliveryPerson person = new DeliveryPerson();
        person.setId(entity.getId());
        person.setName(entity.getName());
        person.setPhoneNumber(entity.getPhoneNumber());
        person.setVehicleType(entity.getVehicleType());
        person.setLicensePlate(entity.getLicensePlate());
        return person;
    }

    public static DeliveryPersonEntity toEntity(DeliveryPerson person) {
        DeliveryPersonEntity entity = new DeliveryPersonEntity();
        entity.setId(person.getId());
        entity.setName(person.getName());
        entity.setPhoneNumber(person.getPhoneNumber());
        entity.setVehicleType(person.getVehicleType());
        entity.setLicensePlate(person.getLicensePlate());
        return entity;
    }
}
