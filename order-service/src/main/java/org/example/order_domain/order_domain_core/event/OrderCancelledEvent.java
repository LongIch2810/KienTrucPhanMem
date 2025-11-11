package org.example.order_domain.order_domain_core.event;

import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class OrderCancelledEvent implements DomainEvent {
    private UUID orderId;
    
    private String failureMessage;
    
    private Instant occurredOn;

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

    // Getters and Setters
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

    public void setOccurredOn(Instant occurredOn) {
        this.occurredOn = occurredOn;
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
