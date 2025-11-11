package org.example.payment_domain.payment_domain_core.entity;

import org.example.payment_domain.payment_domain_core.enums.PaymentMethod;
import org.example.payment_domain.payment_domain_core.enums.PaymentStatus;
import org.example.payment_domain.payment_domain_core.exception.PaymentInvalidArgumentException;
import org.example.payment_domain.payment_domain_core.exception.PaymentInvalidStateException;
import org.example.payment_domain.payment_domain_core.exception.PaymentTransactionException;
import org.example.payment_domain.payment_domain_core.valueobject.*;

import java.util.List;

import java.time.LocalDateTime;
import java.util.UUID;

public class Payment {
    private PaymentId id;
    private OrderId orderId;
    private CustomerId customerId;
    private RestaurantId restaurantId;
    private Money amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private List<OrderItem> orderItems;
    private UUID transactionId;
    private String failureReason;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Payment(){}

    public Payment(PaymentId id, OrderId orderId, CustomerId customerId, RestaurantId restaurantId, 
                  Money amount, PaymentMethod paymentMethod, PaymentStatus paymentStatus, 
                  List<OrderItem> orderItems, UUID transactionId, String failureReason, 
                  LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.orderId = orderId;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.orderItems = orderItems;
        this.transactionId = transactionId;
        this.failureReason = failureReason;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Payment(OrderId orderId, CustomerId customerId, RestaurantId restaurantId, 
                  Money amount, PaymentMethod paymentMethod, List<OrderItem> orderItems) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.orderItems = orderItems;
        this.paymentStatus = PaymentStatus.PENDING;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void markAsCompleted(UUID transactionId) {
        if (this.paymentStatus != PaymentStatus.PENDING) {
            throw new PaymentInvalidStateException(
                "Payment must be in PENDING status to be marked as COMPLETED. Current status: " + this.paymentStatus
            );
        }
        if (transactionId == null) {
            throw new PaymentInvalidArgumentException("Transaction ID cannot be null for completed payment");
        }
        this.paymentStatus = PaymentStatus.COMPLETED;
        this.transactionId = transactionId;
        this.updatedAt = LocalDateTime.now();
    }

    public void markAsFailed(String failureReason) {
        if (this.paymentStatus != PaymentStatus.PENDING) {
            throw new PaymentInvalidStateException(
                "Payment must be in PENDING status to be marked as FAILED. Current status: " + this.paymentStatus
            );
        }
        if (failureReason == null || failureReason.trim().isEmpty()) {
            throw new PaymentInvalidArgumentException("Failure reason cannot be null or empty");
        }
        this.paymentStatus = PaymentStatus.FAILED;
        this.failureReason = failureReason;
        this.updatedAt = LocalDateTime.now();
    }

    public void markAsRefunded(String failureMessage) {
        if (this.paymentStatus != PaymentStatus.COMPLETED) {
            throw new PaymentInvalidStateException(
                "Payment must be in COMPLETED status to be refunded. Current status: " + this.paymentStatus
            );
        }
        if (this.transactionId == null) {
            throw new PaymentTransactionException("Cannot refund payment without transaction ID");
        }
        this.paymentStatus = PaymentStatus.REFUNDED;
        this.failureReason = failureMessage;
        this.updatedAt = LocalDateTime.now();
    }
    
    public void markAsCancelled(String reason) {
        if (this.paymentStatus == PaymentStatus.COMPLETED) {
            throw new PaymentInvalidStateException(
                "Cannot cancel COMPLETED payment. Use refund instead. Current status: " + this.paymentStatus
            );
        }
        if (this.paymentStatus == PaymentStatus.REFUNDED) {
            throw new PaymentInvalidStateException(
                "Cannot cancel REFUNDED payment. Current status: " + this.paymentStatus
            );
        }
        this.paymentStatus = PaymentStatus.CANCELLED;
        this.failureReason = reason;
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public PaymentId getId() {
        return id;
    }

    public void setId(PaymentId id) {
        this.id = id;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public void setOrderId(OrderId orderId) {
        this.orderId = orderId;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public void setCustomerId(CustomerId customerId) {
        this.customerId = customerId;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
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

    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(RestaurantId restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}

