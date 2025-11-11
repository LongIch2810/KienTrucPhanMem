package org.example.restaurant_domain.restaurant_domain_core.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class OrderCreatedEvent {
    private final UUID orderId;
    private final UUID customerId;
    private final UUID restaurantId;
    private final UUID trackingId;
    private final BigDecimal price;
    private final String orderStatus;
    private final List<OrderItemInfo> items;
    private final Instant occurredOn;

    @JsonCreator
    public OrderCreatedEvent(
            @JsonProperty("orderId") UUID orderId,
            @JsonProperty("customerId") UUID customerId,
            @JsonProperty("restaurantId") UUID restaurantId,
            @JsonProperty("trackingId") UUID trackingId,
            @JsonProperty("price") BigDecimal price,
            @JsonProperty("orderStatus") String orderStatus,
            @JsonProperty("items") List<OrderItemInfo> items,
            @JsonProperty("occurredOn") Instant occurredOn
    ) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.trackingId = trackingId;
        this.price = price;
        this.orderStatus = orderStatus;
        this.items = items;
        this.occurredOn = occurredOn;
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

    public Instant getOccurredOn() {
        return occurredOn;
    }

    @Override
    public String toString() {
        return "OrderCreatedEvent{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", restaurantId=" + restaurantId +
                ", trackingId=" + trackingId +
                ", price=" + price +
                ", orderStatus='" + orderStatus + '\'' +
                ", items=" + items +
                ", occurredOn=" + occurredOn +
                '}';
    }

    // Inner class for order item information
    public static class OrderItemInfo {
        private UUID productId;
        private Integer quantity;
        private BigDecimal price;
        private BigDecimal subTotal;

        public OrderItemInfo() {
        }

        @JsonCreator
        public OrderItemInfo(
                @JsonProperty("productId") UUID productId,
                @JsonProperty("quantity") Integer quantity,
                @JsonProperty("price") BigDecimal price,
                @JsonProperty("subTotal") BigDecimal subTotal
        ) {
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

