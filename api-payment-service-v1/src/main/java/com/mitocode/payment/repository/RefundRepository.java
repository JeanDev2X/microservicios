package com.mitocode.payment.repository;

import com.mitocode.payment.domain.Refund;
import com.mitocode.payment.infraestructure.entity.RefundEntity;
import com.mitocode.payment.infraestructure.repository.RefundRepositoryJPA;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class RefundRepository {

    private final RefundRepositoryJPA repository;

    public void save(String orderId, Refund refund) {
        // convertir dominio -> entidad
        RefundEntity entity = new RefundEntity();
        entity.setOrderId(orderId);
        entity.setCustomerId(refund.getCustomerId());
        entity.setCardId(refund.getCardId());
        entity.setAmount(refund.getAmount());

        repository.save(entity);
    }
}
