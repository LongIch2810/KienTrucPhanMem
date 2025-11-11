package org.example.restaurant_domain.restaurant_domain_core.event;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RestaurantRejectedEvent implements DomainEvent {
    private UUID restaurantOrderId;
    private UUID orderId;
    private UUID restaurantId;
    private BigDecimal totalAmount;
    private String approvalStatus;
    private String rejectionReason;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private Instant occurredOn;

    @JsonCreator
    public RestaurantRejectedEvent(
             UUID restaurantOrderId,
             UUID orderId,
           UUID restaurantId,
             BigDecimal totalAmount,
             String approvalStatus,
             String rejectionReason,
             OffsetDateTime createdAt,
             OffsetDateTime updatedAt) {
        this.restaurantOrderId = restaurantOrderId;
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.totalAmount = totalAmount;
        this.approvalStatus = approvalStatus;
        this.rejectionReason = rejectionReason;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.occurredOn = Instant.now();
    }

    @Override
    public String topic() {
        return "restaurant.rejected";
    }

    // Getters and Setters
    public UUID getRestaurantOrderId() {
        return restaurantOrderId;
    }

    public void setRestaurantOrderId(UUID restaurantOrderId) {
        this.restaurantOrderId = restaurantOrderId;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(UUID restaurantId) {
        this.restaurantId = restaurantId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("occurredOn")
    @Override
    public Instant occurredOn() {
        return occurredOn;
    }

    public void setOccurredOn(Instant occurredOn) {
        this.occurredOn = occurredOn;
    }

    @Override
    public String toString() {
        return "RestaurantRejectedEvent{" +
                "restaurantOrderId=" + restaurantOrderId +
                ", orderId=" + orderId +
                ", restaurantId=" + restaurantId +
                ", totalAmount=" + totalAmount +
                ", approvalStatus='" + approvalStatus + '\'' +
                ", rejectionReason='" + rejectionReason + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", occurredOn=" + occurredOn +
                '}';
    }
}
