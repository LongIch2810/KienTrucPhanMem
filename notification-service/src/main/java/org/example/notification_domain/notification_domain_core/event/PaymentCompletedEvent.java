package org.example.notification_domain.notification_domain_core.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentCompletedEvent implements DomainEvent {
    private UUID paymentId;
    private UUID orderId;
    private BigDecimal amount;
    private UUID transactionId;
    private LocalDateTime completedAt;
    private Instant occurredOn;

    public PaymentCompletedEvent() {
    }

    @JsonCreator
    public PaymentCompletedEvent(
            @JsonProperty("paymentId") UUID paymentId,
            @JsonProperty("orderId") UUID orderId,
            @JsonProperty("amount") BigDecimal amount,
            @JsonProperty("transactionId") UUID transactionId,
            @JsonProperty("completedAt") LocalDateTime completedAt,
            @JsonProperty("occurredOn") Instant occurredOn
    ) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.amount = amount;
        this.transactionId = transactionId;
        this.completedAt = completedAt;
        this.occurredOn = occurredOn != null ? occurredOn : Instant.now();
    }

    @Override
    public String topic() {
        return "payment.completed";
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(UUID paymentId) {
        this.paymentId = paymentId;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    @Override
    public String toString() {
        return "PaymentCompletedEvent{" +
                "paymentId=" + paymentId +
                ", orderId=" + orderId +
                ", amount=" + amount +
                ", transactionId=" + transactionId +
                ", completedAt=" + completedAt +
                ", occurredOn=" + occurredOn +
                '}';
    }
}

