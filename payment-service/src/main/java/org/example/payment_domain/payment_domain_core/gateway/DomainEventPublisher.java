package org.example.payment_domain.payment_domain_core.gateway;

import org.example.payment_domain.payment_domain_core.event.DomainEvent;

public interface DomainEventPublisher {
    void publish(DomainEvent event);
}

