package com.mitocode.payment.service;

import com.mitocode.payment.client.VisaRestTemplateClient;
import com.mitocode.payment.domain.Charge;
import com.mitocode.payment.producer.payment.completed.PaymentCompletedProducer;
import com.mitocode.payment.repository.ChargeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentService {

    private static final String COMPLETED_STATUS = "COMPLETED";

    private final ChargeRepository chargeRepository;
    private final VisaRestTemplateClient visaClient;
    private final PaymentCompletedProducer paymentCompletedProducer;

    public void charge(UUID orderId, Charge charge) {

        visaClient.charge(charge.getCardId(), charge.getAmount());

        Charge chargeMutated = charge.toBuilder().status(COMPLETED_STATUS).build();

        chargeRepository.save(chargeMutated);

        paymentCompletedProducer.produce(orderId.toString(), chargeMutated);
    }

}
