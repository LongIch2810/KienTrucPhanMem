package org.example.payment_domain.payment_domain_core.event;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class RestaurantRejectedEvent implements DomainEvent {
    @JsonProperty("restaurantOrderId")
    private UUID restaurantOrderId;
    
    @JsonProperty("orderId")
    private UUID orderId;
    
    @JsonProperty("restaurantId")
    private UUID restaurantId;
    
    @JsonProperty("totalAmount")
    private BigDecimal totalAmount;
    
    @JsonProperty("approvalStatus")
    private String approvalStatus;
    
    @JsonProperty("rejectionReason")
    private String rejectionReason;
    
    @JsonProperty("createdAt")
    private OffsetDateTime createdAt;
    
    @JsonProperty("updatedAt")
    private OffsetDateTime updatedAt;
    
    @JsonProperty("occurredOn")
    private Instant occurredOn;

    @JsonCreator
    public RestaurantRejectedEvent(
            @JsonProperty("restaurantOrderId") UUID restaurantOrderId,
            @JsonProperty("orderId") UUID orderId,
            @JsonProperty("restaurantId") UUID restaurantId,
            @JsonProperty("totalAmount") BigDecimal totalAmount,
            @JsonProperty("approvalStatus") String approvalStatus,
            @JsonProperty("rejectionReason") String rejectionReason,
            @JsonProperty("createdAt") OffsetDateTime createdAt,
            @JsonProperty("updatedAt") OffsetDateTime updatedAt,
            @JsonProperty("occurredOn") Instant occurredOn) {
        this.restaurantOrderId = restaurantOrderId;
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.totalAmount = totalAmount;
        this.approvalStatus = approvalStatus;
        this.rejectionReason = rejectionReason;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.occurredOn = occurredOn != null ? occurredOn : Instant.now();
    }

    @Override
    public String topic() {
        return "restaurant.rejected";
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
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
