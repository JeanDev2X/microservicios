package com.mitocode.order.listener.payment.refund;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.order.infraestructure.document.CardDocument;
import com.mitocode.order.infraestructure.document.OrderDocument;
import com.mitocode.order.infraestructure.document.RefundDocument;
import com.mitocode.order.infraestructure.repository.OrderRepository;
import com.mitocode.order.listener.payment.refund.event.RefundCompletedEvent;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class RefundCompletedListener {

    private final OrderRepository orderRepository;
    private final ObjectMapper mapper;

    @KafkaListener(topics = "payment.refund-completed.v1", groupId = "payment.refund-completed.v1.group")
    public void handle(String message) {
        try {
            log.info("Updating Refund Completed info from message: {}", message);
            RefundCompletedEvent event = mapper.readValue(message, RefundCompletedEvent.class);

            OrderDocument orderDocument = orderRepository.findById(event.getOrderId()).orElseThrow(() -> new NotFoundException("Order Not Found"));

            RefundDocument refund = new RefundDocument();

            CardDocument cardDocument = new CardDocument();
            cardDocument.setId(event.getRefund().getCardId());
            refund.setCard(cardDocument);
            refund.setAmount(event.getRefund().getAmount());

            orderDocument.getPayment().setRefund(refund);
            orderDocument.getPayment().setStatus(event.getStatus());

            orderRepository.save(orderDocument);
        } catch (Exception ex) {
            log.error("Error trying to process Refund Completed Event", ex);
        }
    }
}

