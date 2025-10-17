package org.example.notification_domain.notification_domain_core.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class OrderCreatedEvent implements DomainEvent {
    private final  Long orderId;
    private final  Instant occurredOn;

    @JsonCreator
    public OrderCreatedEvent(
            @JsonProperty("orderId") Long orderId,
            @JsonProperty("occurredOn") Instant occurredOn
    ) {
        this.orderId = orderId;
        this.occurredOn = occurredOn;
    }


    public Long getOrderId() {
        return orderId;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }

    @Override
    public String topic() {
        return "order.created";
    }

    @Override
    public String toString() {
        return "OrderCreatedEvent{" +
                "orderId=" + orderId +
                ", occurredOn=" + occurredOn+
                '}';
    }
}
