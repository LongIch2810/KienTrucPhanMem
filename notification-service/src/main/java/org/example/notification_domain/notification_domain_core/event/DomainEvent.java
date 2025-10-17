package org.example.notification_domain.notification_domain_core.event;

import java.time.Instant;

public interface DomainEvent {
    Instant occurredOn();
    String topic();
}
