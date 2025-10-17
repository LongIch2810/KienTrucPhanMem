package org.example.order_domain.order_domain_core.event;

import java.time.Instant;

public interface DomainEvent {
    Instant occurredOn();
    String topic();
}
