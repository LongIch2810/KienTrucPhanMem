package org.example.order_domain.order_domain_core.entity;

import org.example.order_domain.order_domain_core.enums.OrderStatus;
import org.example.order_domain.order_domain_core.event.DomainEvent;
import org.example.order_domain.order_domain_core.event.OrderCreatedEvent;
import org.example.order_domain.order_domain_core.exception.InvalidOrderStatusException;
import org.example.order_domain.order_domain_core.valueobject.CustomerId;
import org.example.order_domain.order_domain_core.valueobject.Money;
import org.example.order_domain.order_domain_core.valueobject.OrderId;
import org.example.order_domain.order_domain_core.valueobject.RestaurantId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private OrderId id;
    private CustomerId customerId;
    private RestaurantId restaurantId;
    private UUID trackingId;
    private Money price;
    private OrderStatus orderStatus;
    private String failureMessages;
    private LocalDateTime createdAt;
    private List<OrderItem> orderItems = new ArrayList<>();
    private List<DomainEvent> domainEvents = new ArrayList<>();

    public Order(OrderId id, CustomerId customerId, RestaurantId restaurantId,
            UUID trackingId, Money price, OrderStatus orderStatus,
            String failureMessages, LocalDateTime createdAt, List<OrderItem> orderItems) {
        this.id = id;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.trackingId = trackingId;
        this.price = price;
        this.orderStatus = orderStatus;
        this.failureMessages = failureMessages;
        this.createdAt = createdAt;
        this.orderItems = orderItems;
    }

    public Order(CustomerId customerId, RestaurantId restaurantId,
            UUID trackingId, Money price, OrderStatus orderStatus,
            String failureMessages, List<OrderItem> orderItems) {
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.trackingId = trackingId;
        this.price = price;
        this.orderStatus = orderStatus;
        this.failureMessages = failureMessages;
        this.createdAt = LocalDateTime.now();
        this.orderItems = orderItems;
    }

    public Order(CustomerId customerId, RestaurantId restaurantId,
            UUID trackingId, Money price, OrderStatus orderStatus,
            String failureMessages) {
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.trackingId = trackingId;
        this.price = price;
        this.orderStatus = orderStatus;
        this.failureMessages = failureMessages;
        this.createdAt = LocalDateTime.now();
    }

    public OrderId getId() {
        return id;
    }

    public void setId(OrderId id) {
        this.id = id;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public void setCustomerId(CustomerId customerId) {
        this.customerId = customerId;
    }

    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(RestaurantId restaurantId) {
        this.restaurantId = restaurantId;
    }

    public UUID getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(UUID trackingId) {
        this.trackingId = trackingId;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getFailureMessages() {
        return failureMessages;
    }

    public void setFailureMessages(String failureMessages) {
        this.failureMessages = failureMessages;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<DomainEvent> getDomainEvents() {
        return domainEvents;
    }

    public void markCreated() {
        if (this.id != null) {
            domainEvents.add(new OrderCreatedEvent(this));
        }
    }

    public void markPaid() {
        if (this.orderStatus != OrderStatus.PENDING) {
            throw new InvalidOrderStatusException(
                    "Order must be in PENDING status to be marked as PAID.",
                    this.orderStatus,
                    OrderStatus.PENDING);
        }
        this.orderStatus = OrderStatus.PAID;
    }

    public void markApproved() {
        if (this.orderStatus != OrderStatus.PAID) {
            throw new InvalidOrderStatusException(
                    "Order must be in PAID status to be marked as APPROVED.",
                    this.orderStatus,
                    OrderStatus.PAID);
        }
        this.orderStatus = OrderStatus.APPROVED;
    }

    public void markCancelling() {
        if (this.orderStatus == OrderStatus.CANCELLED || this.orderStatus == OrderStatus.CANCELLING) {
            throw new InvalidOrderStatusException(
                    "Order is already being cancelled or cancelled.",
                    this.orderStatus);
        }
        this.orderStatus = OrderStatus.CANCELLING;
    }

    public void markCancelled(String failureMessage) {
        if (this.orderStatus == OrderStatus.PAID) {
            throw new InvalidOrderStatusException(
                    "Order cannot be cancelled when in PAID status.",
                    this.orderStatus);
        }
        this.orderStatus = OrderStatus.CANCELLED;
        this.failureMessages = failureMessage;
    }
}
