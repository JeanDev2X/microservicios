package com.mitocode.service;

import com.mitocode.dto.ClientSummaryDTO;
import com.mitocode.dto.CreateOrderRequest;
import com.mitocode.entity.Client;
import com.mitocode.entity.Order;
import com.mitocode.entity.OrderItem;
import com.mitocode.entity.Product;
import com.mitocode.repository.ClientRepository;
import com.mitocode.repository.OrderRepository;
import com.mitocode.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepo;
    private final ClientRepository clientRepo;
    private final ProductRepository productRepo;

    @PersistenceContext
    private EntityManager em;

    public OrderService(OrderRepository orderRepo, ClientRepository clientRepo, ProductRepository productRepo) {
        this.orderRepo = orderRepo;
        this.clientRepo = clientRepo;
        this.productRepo = productRepo;
    }

    public Page<Order> list(Pageable pageable) {
        return orderRepo.findAll(pageable);
    }

    public List<Order> findByStatus(String status) {
        return orderRepo.findByStatus(status);
    }

    public List<Order> findBetween(LocalDateTime start, LocalDateTime end) {
        return orderRepo.findByDateBetween(start, end);
    }

    public long countNativeByStatus(String status) {
        return orderRepo.countByStatusNative(status);
    }

    @Transactional
    public Order create(CreateOrderRequest req) {
        Client client = clientRepo.findById(req.clientId())
                .orElseThrow(() -> new IllegalArgumentException("Client not found: " + req.clientId()));

        Order order = new Order();
        order.setClient(client);
        order.setStatus(req.status());
        order.setDate(LocalDateTime.now());

        List<OrderItem> items = new ArrayList<>();
        for (CreateOrderRequest.Item it : req.items()) {
            Product p = productRepo.findById(it.productId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found: " + it.productId()));
            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setProduct(p);
            oi.setQuantity(it.quantity());
            items.add(oi);
        }
        order.setItems(items);

        return orderRepo.save(order);
    }

    // Proyección/DTO calculada vía JPQL en runtime (ejemplo pedagógico con EntityManager)
    public List<ClientSummaryDTO> summarizeClients() {
        // COUNT(o) por cliente (LEFT JOIN para incluir clientes sin órdenes)
        var jpql = """
                  SELECT new com.mitocode.dto.ClientSummaryDTO(c.name, COUNT(o))
                  FROM Client c LEFT JOIN c.orders o
                  GROUP BY c.id, c.name
                  ORDER BY c.name
                """;
        return em.createQuery(jpql, ClientSummaryDTO.class).getResultList();
    }

    // Batch insert (sección 7) – ejemplo usando EntityManager
    @Transactional
    public void batchInsertProducts(List<Product> products, int batchSize) {
        int i = 0;
        for (Product p : products) {
            em.persist(p);
            i++;
            if (i % batchSize == 0) {
                em.flush();
                em.clear();
            }
        }
    }
}