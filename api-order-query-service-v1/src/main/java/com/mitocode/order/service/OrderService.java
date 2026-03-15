package com.mitocode.order.service;

import com.mitocode.order.controller.dto.*;
import com.mitocode.order.infraestructure.repository.OrderRepository;
//import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    public OrderResponse getById(String id) {
        return repository.findById(id)
                .map(document ->
                        OrderResponse.builder()
                                .id(document.getId())
                                .customer(
                                        CustomerResponse.builder()
                                                .id(document.getCustomer().getId())
                                                .name(document.getCustomer().getName())
                                                .build()
                                )
                                .restaurant(
                                        RestaurantResponse.builder()
                                                .id(document.getRestaurant().getId())
                                                .name(document.getRestaurant().getName())
                                                .build()
                                )
                                .payment(
                                        document.getPayment() != null ?
                                                PaymentResponse.builder()
                                                        .card(document.getPayment().getCard() != null ?
                                                                CardResponse.builder()
                                                                        .id(document.getPayment().getCard().getId())
                                                                        .build() : null
                                                        )
                                                        .amount(document.getPayment().getAmount())
                                                        .status(document.getPayment().getStatus())
                                                        .description(document.getPayment().getDescription())
                                                        .build() : null
                                )
                                .items(
                                        document.getItems() != null ? document.getItems()
                                                .stream()
                                                .map(item ->
                                                        OrderItemResponse.builder()
                                                                .product(
                                                                        ProductResponse.builder()
                                                                                .id(item.getProduct().getId())
                                                                                .name(item.getProduct().getName())
                                                                                .build()
                                                                )
                                                                .quantity(item.getQuantity())
                                                                .description(item.getDescription())
                                                                .build()
                                                )
                                                .collect(Collectors.toList()) : null
                                )
                                .deliveryAddress(
                                        document.getDeliveryAddress() != null ? DeliveryAddressResponse.builder()
                                                .address(document.getDeliveryAddress().getAddress())
                                                .latitude(document.getDeliveryAddress().getLatitude())
                                                .longitude(document.getDeliveryAddress().getLongitude())
                                                .reference(document.getDeliveryAddress().getReference())
                                                .build() : null
                                )
                                .deliveryPerson(
                                        document.getDeliveryPerson() != null ? DeliveryPersonResponse.builder()
                                                .id(document.getDeliveryPerson().getId())
                                                .name(document.getDeliveryPerson().getName())
                                                .phoneNumber(document.getDeliveryPerson().getPhoneNumber())
                                                .vehicleType(document.getDeliveryPerson().getVehicleType())
                                                .licensePlate(document.getDeliveryPerson().getLicensePlate())
                                                .build() : null
                                )
                                .deliveryStatus(document.getDeliveryStatus())
                                .status(document.getStatus())
                                .total(document.getTotal())
                                .build()
                )
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND, "Order Not Found"));
    }
}
