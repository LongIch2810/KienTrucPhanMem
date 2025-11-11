package org.example.payment_domain.payment_application_service.dto;

import org.example.payment_domain.payment_domain_core.entity.OrderItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class PaymentCreatedDto {
    private UUID orderId;
    private UUID customerId;
    private UUID restaurantId;
    private BigDecimal amount;
    private String paymentMethod;
    private List<OrderItem> orderItems;

    public PaymentCreatedDto() {
    }

    public PaymentCreatedDto(UUID orderId, UUID customerId, UUID restaurantId,
                          BigDecimal amount, String paymentMethod, List<OrderItem> orderItems) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.orderItems = orderItems;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(UUID restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
