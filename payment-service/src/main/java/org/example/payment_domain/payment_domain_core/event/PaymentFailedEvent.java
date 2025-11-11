package org.example.payment_domain.payment_domain_core.event;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.payment_domain.payment_domain_core.enums.PaymentMethod;
import org.example.payment_domain.payment_domain_core.enums.PaymentStatus;
import org.example.payment_domain.payment_domain_core.entity.OrderItem;
import java.util.List;

public class PaymentFailedEvent implements DomainEvent {
    private UUID paymentId;
    private UUID orderId;
    private UUID customerId;
    private BigDecimal amount;
    private UUID transactionId;
    private PaymentStatus status;
    private PaymentMethod paymentMethod;
    private List<OrderItem> items;
    private String failureReason;
    private LocalDateTime failedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Instant occurredOn;


    public PaymentFailedEvent(UUID paymentId, UUID orderId, UUID customerId,
                             BigDecimal amount, UUID transactionId, PaymentStatus status,
                             PaymentMethod paymentMethod, List<OrderItem> items,
                             String failureReason, LocalDateTime failedAt,
                             LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.customerId = customerId;
        this.amount = amount;
        this.transactionId = transactionId;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.items = items;
        this.failureReason = failureReason;
        this.failedAt = failedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.occurredOn = Instant.now();
    }

    @Override
    public String topic() {
        return "payment.failed";
    }

    @JsonProperty("occurredOn")
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

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "PaymentFailedEvent{" +
                "paymentId=" + paymentId +
                ", orderId=" + orderId +
                ", customerId=" + customerId +
                ", amount=" + amount +
                ", transactionId=" + transactionId +
                ", status=" + status +
                ", paymentMethod=" + paymentMethod +
                ", failureReason='" + failureReason + '\'' +
                ", failedAt=" + failedAt +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", items=" + items +
                '}';
    }
}

