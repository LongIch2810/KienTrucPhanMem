package org.example.order_domain.order_domain_core.gateway;

import org.example.order_domain.order_domain_core.event.DomainEvent;

public interface DomainEventPublisher {
    void publish(DomainEvent event);
}
