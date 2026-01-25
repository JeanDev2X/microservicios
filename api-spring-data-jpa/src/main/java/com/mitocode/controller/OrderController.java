package com.mitocode.controller;

import com.mitocode.dto.ClientSummaryDTO;
import com.mitocode.dto.CreateOrderRequest;
import com.mitocode.dto.OrderResponse;
import com.mitocode.entity.Order;
import com.mitocode.entity.Product;
import com.mitocode.mapper.OrderMapper;
import com.mitocode.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    // Paginación (sección 3)
    @GetMapping
    public Page<OrderResponse> page(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "5") int size) {
        return service.list(PageRequest.of(page, size))
                .map(OrderMapper::toOrderResponse);
    }

    // Query Methods (sección 2)
    @GetMapping("/status/{status}")
    public List<OrderResponse> byStatus(@PathVariable String status) {
        return service.findByStatus(status).stream()
                .map(OrderMapper::toOrderResponse)
                .collect(Collectors.toList());
    }

    // Rango de fechas (sección 5)
    @GetMapping("/between")
    public List<OrderResponse> between(@RequestParam String start, @RequestParam String end) {
        return service.findBetween(LocalDateTime.parse(start), LocalDateTime.parse(end)).stream()
                .map(OrderMapper::toOrderResponse)
                .collect(Collectors.toList());
    }

    // Native Query count (sección 5)
    @GetMapping("/count-native")
    public long countNative(@RequestParam String status) {
        return service.countNativeByStatus(status);
    }

    // Crear pedido con items (sección 4 relaciones)
    @PostMapping
    public OrderResponse create(@RequestBody CreateOrderRequest req) {
        Order created = service.create(req);
        return OrderMapper.toOrderResponse(created);
    }

    // Proyección/DTO (sección 6)
    @GetMapping("/client-summary")
    public List<ClientSummaryDTO> clientSummary() {
        return service.summarizeClients();
    }

    // Batch insert con EntityManager (sección 7)
    @PostMapping("/products/batch")
    public void batchProducts(@RequestBody List<Product> products,
                              @RequestParam(defaultValue = "25") int batchSize) {
        service.batchInsertProducts(products, batchSize);
    }
}
