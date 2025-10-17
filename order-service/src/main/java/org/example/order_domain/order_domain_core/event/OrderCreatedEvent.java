package org.example.order_domain.order_domain_core.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.order_domain.order_domain_core.entity.Order;

import java.time.Instant;

public class OrderCreatedEvent implements DomainEvent {
    private final Long orderId;
    private final Instant occurredOn;
    public OrderCreatedEvent(Long orderId) {
        this.orderId = orderId;
        this.occurredOn = Instant.now();
    }

    public Long getOrderId() {
        return orderId;
    }


    @JsonProperty("occurredOn")
    @Override
    public Instant occurredOn() {
        return occurredOn;
    }

    @Override
    public String topic() {
        return "order.created";
    }
}
