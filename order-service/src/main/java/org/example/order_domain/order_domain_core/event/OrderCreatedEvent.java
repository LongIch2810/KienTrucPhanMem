package org.example.order_domain.order_domain_core.event;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.example.order_domain.order_domain_core.entity.Order;

public class OrderCreatedEvent implements DomainEvent {
    private final UUID orderId;
    private final UUID customerId;
    private final UUID restaurantId;
    private final UUID trackingId;
    private final BigDecimal price;
    private final String orderStatus;
    private final List<OrderItemInfo> items;
    private final Instant occurredOn;

    public OrderCreatedEvent(Order order) {
        this.orderId = order.getId() != null ? order.getId().getValue() : null;
        this.customerId = order.getCustomerId() != null ? order.getCustomerId().getValue() : null;
        this.restaurantId = order.getRestaurantId() != null ? order.getRestaurantId().getValue() : null;
        this.trackingId = order.getTrackingId();
        this.price = order.getPrice() != null ? order.getPrice().getAmount() : BigDecimal.ZERO;
        this.orderStatus = order.getOrderStatus() != null ? order.getOrderStatus().getValue() : "PENDING";
        this.items = order.getOrderItems() != null ?
            order.getOrderItems().stream()
                .map(item -> new OrderItemInfo(
                    item.getProductId() != null ? item.getProductId().getValue() : null,
                    item.getQuantity() != null ? item.getQuantity().getValue() : 0,
                    item.getPrice() != null ? item.getPrice().getAmount() : BigDecimal.ZERO,
                    item.getSubTotal() != null ? item.getSubTotal().getAmount() : BigDecimal.ZERO
                ))
                .collect(Collectors.toList()) : List.of();
        this.occurredOn = Instant.now();
    }

    public UUID getOrderId() {
        return orderId;
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

    public List<OrderItemInfo> getItems() {
        return items;
    }

    @JsonProperty("occurredOn")
    @Override
    public Instant occurredOn() {
        return occurredOn;
    }

    @Override
    public String topic() {
        return "order.created";
    }

    // Inner class for order item information
    public static class OrderItemInfo {
        private UUID productId;
        private Integer quantity;
        private BigDecimal price;
        private BigDecimal subTotal;

        public OrderItemInfo() {
        }

        public OrderItemInfo(UUID productId, Integer quantity, BigDecimal price, BigDecimal subTotal) {
            this.productId = productId;
            this.quantity = quantity;
            this.price = price;
            this.subTotal = subTotal;
        }

        public UUID getProductId() {
            return productId;
        }

        public void setProductId(UUID productId) {
            this.productId = productId;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public BigDecimal getSubTotal() {
            return subTotal;
        }

        public void setSubTotal(BigDecimal subTotal) {
            this.subTotal = subTotal;
        }
    }
}
