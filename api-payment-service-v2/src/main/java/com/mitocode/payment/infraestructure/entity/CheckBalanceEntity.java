package com.mitocode.payment.infraestructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Table(name = "check_balance")
@NoArgsConstructor
@AllArgsConstructor
public class CheckBalanceEntity {

    @Id
    private UUID id;
    private Long customerId;
    private Long cardId;
    private BigDecimal requiredAmount;
    private boolean hasSufficientFunds;

}
