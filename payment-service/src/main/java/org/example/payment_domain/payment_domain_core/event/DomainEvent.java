package org.example.payment_domain.payment_domain_core.event;

import java.time.Instant;

public interface DomainEvent {
    Instant occurredOn();
    String topic();
}

