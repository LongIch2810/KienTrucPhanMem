package org.example.payment_messaging.publisher;

import org.example.payment_domain.payment_domain_core.event.DomainEvent;
import org.example.payment_domain.payment_domain_core.gateway.DomainEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageQueuePublisher implements DomainEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public MessageQueuePublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(DomainEvent event) {
        String topic = event.topic();
        kafkaTemplate.send(topic, event);
    }
}

