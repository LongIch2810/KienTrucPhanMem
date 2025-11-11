package org.example.order_domain.order_domain_core.event;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentFailedEvent implements DomainEvent {
    private UUID paymentId;
    
    private UUID orderId;
    
    private UUID customerId;
    
    private BigDecimal amount;
    
    private UUID transactionId;
    
    private String status;
    
    private String paymentMethod;
    
    private String failureReason;
    
    private LocalDateTime failedAt;
    
    private Instant occurredOn;

    @JsonCreator
    public PaymentFailedEvent(
            @JsonProperty("paymentId") UUID paymentId,
            @JsonProperty("orderId") UUID orderId,
            @JsonProperty("customerId") UUID customerId,
            @JsonProperty("amount") BigDecimal amount,
            @JsonProperty("transactionId") UUID transactionId,
            @JsonProperty("status") String status,
            @JsonProperty("paymentMethod") String paymentMethod,
            @JsonProperty("failureReason") String failureReason,
            @JsonProperty("failedAt") LocalDateTime failedAt,
            @JsonProperty("occurredOn") Instant occurredOn) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.customerId = customerId;
        this.amount = amount;
        this.transactionId = transactionId;
        this.status = status;
        this.paymentMethod = paymentMethod;
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

    // Getters and Setters
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

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
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

    public void setOccurredOn(Instant occurredOn) {
        this.occurredOn = occurredOn;
    }

    @Override
    public String toString() {
        return "PaymentFailedEvent{" +
                "paymentId=" + paymentId +
                ", orderId=" + orderId +
                ", customerId=" + customerId +
                ", amount=" + amount +
                ", transactionId=" + transactionId +
                ", status='" + status + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", failureReason='" + failureReason + '\'' +
                ", failedAt=" + failedAt +
                ", occurredOn=" + occurredOn +
                '}';
    }
}
