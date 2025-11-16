package org.example.restaurant_domain.restaurant_domain_core.entity;

import java.time.OffsetDateTime;

import org.example.restaurant_domain.restaurant_domain_core.enums.OrderApprovalStatus;
import org.example.restaurant_domain.restaurant_domain_core.valueobject.Money;
import org.example.restaurant_domain.restaurant_domain_core.valueobject.OrderId;
import org.example.restaurant_domain.restaurant_domain_core.valueobject.RestaurantId;
import org.example.restaurant_domain.restaurant_domain_core.valueobject.RestaurantOrderId;

public class RestaurantOrder {
    private RestaurantOrderId id;
    private OrderId orderId;
    private RestaurantId restaurantId;
    private Money totalAmount;
    private OrderApprovalStatus approvalStatus;
    private String rejectionReason;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public RestaurantOrder() {
    }

    public RestaurantOrder(
            OrderId orderId,
            RestaurantId restaurantId,
            Money totalAmount,
            OrderApprovalStatus approvalStatus) {
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.totalAmount = totalAmount;
        this.approvalStatus = approvalStatus;
        this.createdAt = OffsetDateTime.now();
    }

    public RestaurantOrder(RestaurantOrderId id,
            OrderId orderId,
            RestaurantId restaurantId,
            Money totalAmount,
            OrderApprovalStatus approvalStatus) {
        this.id = id;
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.totalAmount = totalAmount;
        this.approvalStatus = approvalStatus;
        this.createdAt = OffsetDateTime.now();
    }

    public RestaurantOrder(RestaurantOrderId id,
            OrderId orderId,
            RestaurantId restaurantId,
            Money totalAmount,
            OrderApprovalStatus approvalStatus,
            String rejectionReason,
            OffsetDateTime createdAt,
            OffsetDateTime updatedAt) {
        this.id = id;
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.totalAmount = totalAmount;
        this.approvalStatus = approvalStatus;
        this.rejectionReason = rejectionReason;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public RestaurantOrderId getId() {
        return id;
    }

    public void setId(RestaurantOrderId id) {
        this.id = id;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public void setOrderId(OrderId orderId) {
        this.orderId = orderId;
    }

    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(RestaurantId restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Money getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Money totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(OrderApprovalStatus approvalStatus) {
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

    public void markRejected(String reason) {
        this.approvalStatus = OrderApprovalStatus.REJECTED;
        this.setRejectionReason(reason);
        this.updatedAt = OffsetDateTime.now();
    }

    public void markApproved() {
        this.approvalStatus = OrderApprovalStatus.APPROVED;
        this.updatedAt = OffsetDateTime.now();
    }
}
