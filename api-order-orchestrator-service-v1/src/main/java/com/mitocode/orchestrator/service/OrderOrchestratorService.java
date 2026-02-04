package com.mitocode.orchestrator.service;

import com.mitocode.orchestrator.client.ordes.restclient.dto.CreateOrderResponse;
import com.mitocode.orchestrator.controller.dto.CreateOrderOrchestratorRequest;
import com.mitocode.orchestrator.controller.dto.CreateOrderOrchestratorResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderOrchestratorService {

    private final OrderService orderService;
    private final PaymentService paymentService;
    private final RestaurantService restaurantService;
    /*private final DeliveryService deliveryService;*/

    public CreateOrderOrchestratorResponse createOrder(CreateOrderOrchestratorRequest request) {

        CreateOrderResponse orderCreated = orderService.createOrder(request);

        paymentService.checkBalance(request.customer().id(), request.card().id(), request.total());
        paymentService.charge(request.customer().id(), request.card().id(), request.total());
        restaurantService.reserveRestaurant(orderCreated.id(), request);

        return new CreateOrderOrchestratorResponse(orderCreated.id());
    }

}
