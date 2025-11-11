package org.example.payment_messaging.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfiguration {

    @Bean
    public NewTopic paymentCompletedTopic() {
        return TopicBuilder.name("payment.completed")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic paymentFailedTopic() {
        return TopicBuilder.name("payment.failed")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic paymentCancelledTopic() {
        return TopicBuilder.name("payment.cancelled")
                .partitions(2)
                .replicas(1)
                .build();
    }
}

