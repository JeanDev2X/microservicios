package com.mitocode.payment.service;

import com.mitocode.payment.client.VisaRestTemplateClient;
import com.mitocode.payment.domain.Refund;
import com.mitocode.payment.producer.refund.completed.RefundCompletedProducer;
import com.mitocode.payment.repository.RefundRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RefundService {

    private final RefundRepository refundRepository;
    private final VisaRestTemplateClient visaClient;
    private final RefundCompletedProducer refundCompletedProducer;

    public void refund(String orderId, Refund refund) {

        //Se hace la devolucion del dinero llamando a VISA
        //visaClient.refound(refound.getAccountId(), refound.getAmount());

        refundRepository.save(orderId, refund);
        refundCompletedProducer.produce(orderId, refund);

    }

}
