package org.example.restaurant_messaging.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfiguration {

    @Bean
    public NewTopic restaurantApprovedTopic() {
        return TopicBuilder.name("restaurant.approved")
                .partitions(2)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic restaurantRejectedTopic() {
        return TopicBuilder.name("restaurant.rejected")
                .partitions(2)
                .replicas(1)
                .build();
    }
}

