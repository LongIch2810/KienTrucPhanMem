package org.example.payment_domain.payment_domain_core.event;

import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderCancelledEvent implements DomainEvent {
    private UUID orderId;
    
    private String failureMessage;
    
    private Instant occurredOn;


    public OrderCancelledEvent(UUID orderId, String failureMessage) {
        this.orderId = orderId;
        this.failureMessage = failureMessage;
        this.occurredOn = Instant.now();
    }

    @Override
    public String topic() {
        return "order.cancelled";
    }

    @JsonProperty("occurredOn")
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
