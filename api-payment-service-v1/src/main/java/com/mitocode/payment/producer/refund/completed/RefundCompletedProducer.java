package com.mitocode.payment.producer.refund.completed;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.payment.domain.Refund;
import com.mitocode.payment.producer.refund.completed.event.RefundCompletedEvent;
import com.mitocode.payment.producer.refund.completed.event.RefundEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class RefundCompletedProducer {

    private static final String TOPIC = "payment.refund-completed.v1";

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper mapper;

    public void produce(String orderId, Refund refund) {
        try {
            RefundCompletedEvent event = new RefundCompletedEvent();
            event.setOrderId(orderId);
            event.setRefund(new RefundEvent(refund.getCustomerId(), refund.getCardId(), refund.getAmount()));
            event.setStatus("REFUNDED");

            String message = mapper.writeValueAsString(event);
            kafkaTemplate.send(TOPIC, message);
            log.info("RefundCompletedProducer.produce: orderId={}, refund={}", orderId, message);
        } catch (Exception ex) {
            log.error("Error trying to produce Refund Completed Event", ex);
        }
    }
}
