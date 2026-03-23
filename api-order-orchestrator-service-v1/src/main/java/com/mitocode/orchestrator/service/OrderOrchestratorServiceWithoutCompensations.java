package com.mitocode.orchestrator.service;

import com.mitocode.orchestrator.client.orders.restclient.dto.CreateOrderResponse;
import com.mitocode.orchestrator.controller.dto.CreateOrderOrchestratorRequest;
import com.mitocode.orchestrator.controller.dto.CreateOrderOrchestratorResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service("orchestratorWithoutCompensations")
@AllArgsConstructor
public class OrderOrchestratorServiceWithoutCompensations implements OrderOrchestratorService {

    private final OrderService orderService;
    private final PaymentService paymentService;
    private final RestaurantService restaurantService;
    private final DeliveryService deliveryService;

    @Override
    public CreateOrderOrchestratorResponse createOrder(CreateOrderOrchestratorRequest request) {

        CreateOrderResponse orderCreated = orderService.createOrder(request);

        paymentService.checkBalance(request.customer().id(), request.card().id(), request.total());

        paymentService.charge(orderCreated.id(), request.customer().id(), request.card().id(), request.total());

        restaurantService.reserveRestaurant(orderCreated.id(), request);

        deliveryService.assignDriver(orderCreated.id(), request);

        return new CreateOrderOrchestratorResponse(orderCreated.id());
    }

}
