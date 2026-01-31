package com.mitocode.orchestrator.controller.dto;

public record DeliveryPerson(
        Long id,
        String name,
        String phoneNumber,
        String vehicleType,
        String licensePlate
) {}