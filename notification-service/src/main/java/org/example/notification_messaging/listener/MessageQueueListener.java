package org.example.notification_messaging.listener;

import org.example.notification_domain.notification_domain_core.event.OrderCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageQueueListener {
    @KafkaListener(topics = "${kafka.topics.order-created}",groupId = "${spring.kafka.consumer.group-id}")
    public void orderCreated(OrderCreatedEvent event) {
        System.out.println("📥 Nhận event: " + event.toString());
    }
}
