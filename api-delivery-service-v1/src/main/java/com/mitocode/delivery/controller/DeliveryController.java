package com.mitocode.delivery.controller;

import com.mitocode.delivery.controller.dto.AssignDriverRequest;
import com.mitocode.delivery.controller.dto.DeliveryResponse;
import com.mitocode.delivery.controller.dto.ReleaseDriverRequest;
import com.mitocode.delivery.controller.mapper.DeliveryMapper;
import com.mitocode.delivery.domain.Delivery;
import com.mitocode.delivery.service.DeliveryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping("/assign-driver")
    public ResponseEntity<DeliveryResponse> assignDriver(@RequestBody AssignDriverRequest request) {
        log.info("Assigning driver to delivery for order");
        Delivery delivery = deliveryService.assignDriver(DeliveryMapper.toDomain(request));
        return ResponseEntity.ok(DeliveryMapper.toResponse(delivery));
    }

    @PostMapping("/release-driver")
    public ResponseEntity<DeliveryResponse> releaseDriver(@RequestBody ReleaseDriverRequest request) {
        Delivery delivery = deliveryService.releaseDriver(request.orderId());
        return ResponseEntity.ok(DeliveryMapper.toResponse(delivery));
    }


    @PostMapping("/{orderId}/start")
    public ResponseEntity<DeliveryResponse> startDelivery(@PathVariable UUID orderId) {
        log.info("Starting delivery for order {}", orderId);
        Delivery delivery = deliveryService.startDelivery(orderId);
        return ResponseEntity.ok(DeliveryMapper.toResponse(delivery));
    }

    @PostMapping("/{orderId}/complete")
    public ResponseEntity<DeliveryResponse> completeDelivery(@PathVariable UUID orderId) {
        Delivery delivery = deliveryService.completeDelivery(orderId);
        return ResponseEntity.ok(DeliveryMapper.toResponse(delivery));
    }

}
