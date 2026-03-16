package com.mitocode.payment.repository;

import com.mitocode.payment.domain.Charge;
import com.mitocode.payment.infraestructure.entity.ChargeEntity;
import com.mitocode.payment.infraestructure.repository.ChargeRepositoryJPA;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ChargeRepository {

    private final ChargeRepositoryJPA repository;

    public void save(Charge charge) {
        // convertir dominio -> entidad
        ChargeEntity entity = new ChargeEntity();
        entity.setCustomerId(charge.getCustomerId());
        entity.setCardId(charge.getCardId());
        entity.setAmount(charge.getAmount());
        entity.setStatus(charge.getStatus());

        repository.save(entity);
    }
}
