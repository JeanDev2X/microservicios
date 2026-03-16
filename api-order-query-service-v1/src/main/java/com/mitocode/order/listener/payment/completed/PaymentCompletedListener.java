package com.mitocode.order.listener.payment.completed;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.order.exception.OrderNotFoundException;
import com.mitocode.order.infraestructure.document.CardDocument;
import com.mitocode.order.infraestructure.document.OrderDocument;
import com.mitocode.order.infraestructure.document.PaymentDocument;
import com.mitocode.order.infraestructure.repository.OrderRepository;
import com.mitocode.order.listener.payment.completed.event.PaymentCompletedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class PaymentCompletedListener {

    private final OrderRepository orderRepository;
    private final ObjectMapper mapper;

    @KafkaListener(topics = "payment.payment-completed.v1", groupId = "payment.payment-completed.v1.group")
    public void handle(String message) {
        try {
            log.info("Updating Payment Completed info from message: {}", message);

            PaymentCompletedEvent event = mapper.readValue(message, PaymentCompletedEvent.class);

            //buscar la orden, si no existe lanzar una excepcion
            OrderDocument orderDocument = orderRepository.findById(event.getOrderId()).orElseThrow(() -> new OrderNotFoundException("Order Not Found for id: " + event.getOrderId()));

            CardDocument cardDocument = new CardDocument();
            cardDocument.setId(event.getCharge().getCardId());

            //crear el documento de pago con la informacion del evento
            PaymentDocument paymentDocument = new PaymentDocument();
            paymentDocument.setCard(cardDocument);
            paymentDocument.setAmount(event.getCharge().getAmount());
            paymentDocument.setStatus(event.getCharge().getStatus());

            orderDocument.setPayment(paymentDocument);

            orderRepository.save(orderDocument);
        } catch (Exception ex) {
            log.error("Error trying to process Payment Completed Event", ex);
        }
    }
}
