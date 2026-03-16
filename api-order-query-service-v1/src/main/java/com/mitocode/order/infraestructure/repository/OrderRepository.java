package com.mitocode.order.infraestructure.repository;

import com.mitocode.order.infraestructure.document.OrderDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderDocument, String> {
}
