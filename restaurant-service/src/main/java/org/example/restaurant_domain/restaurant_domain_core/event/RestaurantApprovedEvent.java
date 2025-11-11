package org.example.restaurant_domain.restaurant_domain_core.event;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RestaurantApprovedEvent implements DomainEvent {
    private UUID restaurantOrderId;
    private UUID orderId;
    private UUID restaurantId;
    private BigDecimal totalAmount;
    private String approvalStatus;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private Instant occurredOn;

    @JsonCreator
    public RestaurantApprovedEvent(
             UUID restaurantOrderId,
             UUID orderId,
             UUID restaurantId,
             BigDecimal totalAmount,
             String approvalStatus,
             OffsetDateTime createdAt,
             OffsetDateTime updatedAt) {
        this.restaurantOrderId = restaurantOrderId;
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.totalAmount = totalAmount;
        this.approvalStatus = approvalStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.occurredOn =  Instant.now();
    }

    @Override
    public String topic() {
        return "restaurant.approved";
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
        return "RestaurantApprovedEvent{" +
                "restaurantOrderId=" + restaurantOrderId +
                ", orderId=" + orderId +
                ", restaurantId=" + restaurantId +
                ", totalAmount=" + totalAmount +
                ", approvalStatus='" + approvalStatus + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", occurredOn=" + occurredOn +
                '}';
    }
}
