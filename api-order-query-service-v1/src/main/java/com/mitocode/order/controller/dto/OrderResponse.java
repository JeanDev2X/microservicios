package com.mitocode.order.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponse {

    private String id;
    private CustomerResponse customer;
    private RestaurantResponse restaurant;
    private PaymentResponse payment;
    private List<OrderItemResponse> items;
    private DeliveryAddressResponse deliveryAddress;
    private DeliveryPersonResponse deliveryPerson;
    private String deliveryStatus;
    private String status;
    private BigDecimal total;
}
