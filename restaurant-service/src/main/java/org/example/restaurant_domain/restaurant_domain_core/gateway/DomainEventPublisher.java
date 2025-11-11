package org.example.restaurant_domain.restaurant_domain_core.gateway;

import org.example.restaurant_domain.restaurant_domain_core.event.DomainEvent;

public interface DomainEventPublisher {
    void publish(DomainEvent event);
}

