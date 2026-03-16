package com.mitocode.payment.producer.payment.completed;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.payment.domain.Charge;
import com.mitocode.payment.producer.payment.completed.event.ChargeEvent;
import com.mitocode.payment.producer.payment.completed.event.PaymentCompletedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class PaymentCompletedProducer {

    private static final String TOPIC = "payment.payment-completed.v1";

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper mapper;

    public void produce(String orderId, Charge charge) {
        try {

            PaymentCompletedEvent event = new PaymentCompletedEvent();
            event.setOrderId(orderId);
            event.setCharge(new ChargeEvent(charge.getCustomerId(), charge.getCardId(), charge.getAmount(), charge.getStatus()));

            kafkaTemplate.send(TOPIC, orderId, mapper.writeValueAsString(event));
            log.info("PaymentCompletedProducer.produce: orderId={}", orderId);
        } catch (Exception ex) {
            log.error("Error trying to produce Payment Completed Event", ex);
        }
    }
}
