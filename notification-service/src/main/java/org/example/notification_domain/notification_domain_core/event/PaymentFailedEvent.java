package org.example.notification_domain.notification_domain_core.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentFailedEvent implements DomainEvent {
    private UUID paymentId;
    private UUID orderId;
    private BigDecimal amount;
    private String failureReason;
    private LocalDateTime failedAt;
    private Instant occurredOn;

    public PaymentFailedEvent() {
    }

    @JsonCreator
    public PaymentFailedEvent(
            @JsonProperty("paymentId") UUID paymentId,
            @JsonProperty("orderId") UUID orderId,
            @JsonProperty("amount") BigDecimal amount,
            @JsonProperty("failureReason") String failureReason,
            @JsonProperty("failedAt") LocalDateTime failedAt,
            @JsonProperty("occurredOn") Instant occurredOn
    ) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.amount = amount;
        this.failureReason = failureReason;
        this.failedAt = failedAt;
        this.occurredOn = occurredOn != null ? occurredOn : Instant.now();
    }

    @Override
    public String topic() {
        return "payment.failed";
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

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    public LocalDateTime getFailedAt() {
        return failedAt;
    }

    public void setFailedAt(LocalDateTime failedAt) {
        this.failedAt = failedAt;
    }

    @Override
    public String toString() {
        return "PaymentFailedEvent{" +
                "paymentId=" + paymentId +
                ", orderId=" + orderId +
                ", amount=" + amount +
                ", failureReason='" + failureReason + '\'' +
                ", failedAt=" + failedAt +
                ", occurredOn=" + occurredOn +
                '}';
    }
}

