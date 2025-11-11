package org.example.order_domain.order_application_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderResponseDto {
    private final UUID id;
    private final UUID customerId;
    private final UUID restaurantId;
    private final UUID trackingId;
    private final BigDecimal price;
    private final String orderStatus;
    private final String failureMessages;
    private final LocalDateTime createdAt;
    private List<OrderItemDto> items = new ArrayList<>();

    public OrderResponseDto(UUID id, UUID customerId, UUID restaurantId,
                            UUID trackingId, BigDecimal price, String orderStatus,
                            String failureMessages, LocalDateTime createdAt,
                            List<OrderItemDto> items) {
        this.id = id;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.trackingId = trackingId;
        this.price = price;
        this.orderStatus = orderStatus;
        this.failureMessages = failureMessages;
        this.createdAt = createdAt;
        this.items = items;
    }

    public OrderResponseDto(UUID id, UUID customerId, UUID restaurantId,
                            UUID trackingId, BigDecimal price, String orderStatus,
                            String failureMessages, LocalDateTime createdAt) {
        this.id = id;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.trackingId = trackingId;
        this.price = price;
        this.orderStatus = orderStatus;
        this.failureMessages = failureMessages;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public UUID getTrackingId() {
        return trackingId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getFailureMessages() {
        return failureMessages;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }
}
