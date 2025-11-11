package org.example.payment_domain.payment_domain_core.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.payment_domain.payment_domain_core.entity.OrderItem;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class OrderCreatedEvent implements DomainEvent {
    private final UUID orderId;
    private final UUID customerId;
    private final UUID restaurantId;
    private final UUID trackingId;
    private final BigDecimal price;
    private final String orderStatus;
    private final List<OrderItem> items;
    private final Instant occurredOn;

    @JsonCreator
    public OrderCreatedEvent(
            @JsonProperty("orderId") UUID orderId,
            @JsonProperty("customerId") UUID customerId,
            @JsonProperty("restaurantId") UUID restaurantId,
            @JsonProperty("trackingId") UUID trackingId,
            @JsonProperty("price") BigDecimal price,
            @JsonProperty("orderStatus") String orderStatus,
            @JsonProperty("items") List<OrderItem> items,
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

    public List<OrderItem> getItems() {
        return items;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }

    @Override
    public String topic() {
        return "order.created";
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


}
