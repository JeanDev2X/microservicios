package com.mitocode.payment.producer.payment.failed;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.payment.domain.Charge;
import com.mitocode.payment.producer.payment.failed.event.ChargeEvent;
import com.mitocode.payment.producer.payment.failed.event.PaymentFailedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class PaymentFailedProducer {

    private static final String TOPIC = "payment.payment-failed.v1";

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper mapper;

    public void produce(String orderId, Charge charge, String reason) {
        try {
            PaymentFailedEvent event = new PaymentFailedEvent();
            event.setOrderId(orderId);
            event.setCharge(new ChargeEvent(charge.getCustomerId(), charge.getCardId(), charge.getAmount()));
            event.setStatus("FAILED");
            event.setDescription(reason);
            //event.setDescription("Insufficient funds - VISA Service unavailable");

            String message = mapper.writeValueAsString(event);
            kafkaTemplate.send(TOPIC, message);
            log.info("PaymentCompletedProducer.produce: orderId={}, charge={}", orderId, charge);
        } catch (Exception ex) {
            log.error("Error trying to produce Payment Completed Event", ex);
        }
    }
}
