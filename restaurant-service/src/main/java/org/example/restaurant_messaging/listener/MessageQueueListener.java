package org.example.restaurant_messaging.listener;

import org.example.restaurant_domain.restaurant_application_service.handler.CheckAvailabilityAndApproveOrderUseCase;
import org.example.restaurant_domain.restaurant_domain_core.event.PaymentCompletedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageQueueListener {
    private final CheckAvailabilityAndApproveOrderUseCase checkAvailabilityAndApproveOrderUseCase;

    public MessageQueueListener(CheckAvailabilityAndApproveOrderUseCase checkAvailabilityAndApproveOrderUseCase) {
        this.checkAvailabilityAndApproveOrderUseCase = checkAvailabilityAndApproveOrderUseCase;
    }

    @KafkaListener(topics = "${kafka.topics.payment-completed}", groupId = "${spring.kafka.consumer.group-id}")
    public void handlePaymentCompletedEvent(PaymentCompletedEvent event) {
        System.out.println("[RESTAURANT-SERVICE] Nhận event: " + event.toString());
        checkAvailabilityAndApproveOrderUseCase.execute(event);
    }
}
