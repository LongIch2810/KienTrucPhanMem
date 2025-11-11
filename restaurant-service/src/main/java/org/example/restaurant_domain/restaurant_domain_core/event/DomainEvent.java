package org.example.restaurant_domain.restaurant_domain_core.event;

import java.time.Instant;

public interface DomainEvent {
    String topic();
    Instant occurredOn();
}

