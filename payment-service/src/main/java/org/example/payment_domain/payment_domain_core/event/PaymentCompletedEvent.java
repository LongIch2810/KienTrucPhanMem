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

public class PaymentCompletedEvent implements DomainEvent {
    private UUID paymentId;
    private UUID orderId;
    private UUID customerId;
    private UUID restaurantId;
    private BigDecimal amount;
    private UUID transactionId;
    private PaymentStatus status;
    private PaymentMethod paymentMethod;
    private List<OrderItem> items;
    private LocalDateTime completedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Instant occurredOn;

    public PaymentCompletedEvent(UUID paymentId, 
                               UUID orderId,
                               UUID customerId,
                               UUID restaurantId,
                               BigDecimal amount, 
                               UUID transactionId,
                               PaymentStatus status,
                               PaymentMethod paymentMethod,
                               List<OrderItem> items,
                               LocalDateTime completedAt,
                               LocalDateTime createdAt,
                               LocalDateTime updatedAt) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.amount = amount;
        this.transactionId = transactionId;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.items = items;
        this.completedAt = completedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.occurredOn = Instant.now();
    }

    @Override
    public String topic() {
        return "payment.completed";
    }

    @JsonProperty("occurredOn")
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

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(UUID restaurantId) {
        this.restaurantId = restaurantId;
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

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
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

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "PaymentCompletedEvent{" +
                "paymentId=" + paymentId +
                ", orderId=" + orderId +
                ", customerId=" + customerId +
                ", amount=" + amount +
                ", transactionId=" + transactionId +
                ", status=" + status +
                ", paymentMethod=" + paymentMethod +
                ", completedAt=" + completedAt +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", items=" + items +
                ", occurredOn=" + occurredOn +
                '}';
}
}

