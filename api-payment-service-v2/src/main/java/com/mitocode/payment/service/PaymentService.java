package com.mitocode.payment.service;

import com.mitocode.payment.client.VisaRestTemplateClient;
import com.mitocode.payment.domain.Charge;
import com.mitocode.payment.producer.payment.completed.PaymentCompletedProducer;
import com.mitocode.payment.repository.ChargeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentService {

    private static final String STATUS_COMPLETED = "COMPLETED";

    private final ChargeRepository chargeRepository;
    private final VisaRestTemplateClient visaClient;
    private final PaymentCompletedProducer paymentCompletedProducer;

    public void charge(String orderId, Charge charge) {

        visaClient.charge(charge.getCardId(), charge.getAmount());

        Charge chargeMutated = charge.toBuilder().status(STATUS_COMPLETED).build();

        chargeRepository.save(chargeMutated);

        paymentCompletedProducer.produce(orderId, chargeMutated);
    }

}
