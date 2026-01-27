package com.mitocode.payment.repository;

import com.mitocode.payment.domain.CheckBalance;
import com.mitocode.payment.infraestructure.entity.CheckBalanceEntity;
import com.mitocode.payment.infraestructure.repository.CheckBalanceRepositoryJPA;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CheckBalanceRepository {

    private final CheckBalanceRepositoryJPA repository;

    public boolean saveCheckResult(CheckBalance checkBalance, boolean hasFunds) {
        CheckBalanceEntity entity = new CheckBalanceEntity(
                checkBalance.getId(),
                checkBalance.getCustomerId(),
                checkBalance.getCardId(),
                checkBalance.getRequiredAmount(),
                hasFunds
        );
        repository.save(entity);
        return hasFunds;
    }
}
