package com.mitocode.order.listener.payment.failed;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.order.infraestructure.document.CardDocument;
import com.mitocode.order.infraestructure.document.OrderDocument;
import com.mitocode.order.infraestructure.document.PaymentDocument;
import com.mitocode.order.infraestructure.repository.OrderRepository;
import com.mitocode.order.listener.payment.failed.event.PaymentFailedEvent;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class PaymentFailedListener {

    private final OrderRepository orderRepository;
    private final ObjectMapper mapper;

    @KafkaListener(topics = "payment.payment-failed.v1", groupId = "payment.payment-failed.v1.group")
    public void handle(String message) {
        try {
            log.info("Updating Payment Failed info from message: {}", message);
            PaymentFailedEvent event = mapper.readValue(message, PaymentFailedEvent.class);

            OrderDocument orderDocument = orderRepository.findById(event.getOrderId()).orElseThrow(() -> new NotFoundException("Order Not Found"));

            PaymentDocument payment = new PaymentDocument();

            CardDocument cardDocument = new CardDocument();
            cardDocument.setId(event.getCharge().getCardId());
            payment.setCard(cardDocument);
            payment.setAmount(event.getCharge().getAmount());
            payment.setStatus(event.getStatus());
            payment.setDescription(event.getDescription());

            orderDocument.setPayment(payment);

            orderRepository.save(orderDocument);
        } catch (Exception ex) {
            log.error("Error trying to process Payment Failed Event", ex);
        }
    }
}

