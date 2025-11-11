package org.example.notification_domain.notification_domain_core.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.UUID;

public class OrderCancelledEvent implements DomainEvent {
    private UUID orderId;
    private String failureMessage;
    private Instant occurredOn;

    public OrderCancelledEvent() {
    }

    @JsonCreator
    public OrderCancelledEvent(
            @JsonProperty("orderId") UUID orderId,
            @JsonProperty("failureMessage") String failureMessage,
            @JsonProperty("occurredOn") Instant occurredOn
    ) {
        this.orderId = orderId;
        this.failureMessage = failureMessage;
        this.occurredOn = occurredOn != null ? occurredOn : Instant.now();
    }

    @Override
    public String topic() {
        return "order.cancelled";
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public String getFailureMessage() {
        return failureMessage;
    }

    public void setFailureMessage(String failureMessage) {
        this.failureMessage = failureMessage;
    }

    @Override
    public String toString() {
        return "OrderCancelledEvent{" +
                "orderId=" + orderId +
                ", failureMessage='" + failureMessage + '\'' +
                ", occurredOn=" + occurredOn +
                '}';
    }
}

