package com.mitocode.order.controller;

import com.mitocode.order.dto.request.CreateOrderRequest;
import com.mitocode.order.dto.response.OrderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
        //Im
        return ResponseEntity.status(HttpStatus.CREATED).body(new OrderResponse());
    }

}
