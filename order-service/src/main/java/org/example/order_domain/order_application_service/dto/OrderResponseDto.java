package org.example.order_domain.order_application_service.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderResponseDto {
    private final Long orderId;
    private final Long userId;
    private final BigDecimal totalAmount;
    private final BigDecimal discountAmount;
    private final BigDecimal finalAmount;
    private final String orderStatus;
    private final String paymentStatus;
    private final String paymentMethod;
    private List<OrderItemDto> items = new ArrayList<>();

    public OrderResponseDto(Long orderId, Long userId, BigDecimal totalAmount,
                            BigDecimal discountAmount, BigDecimal finalAmount,
                            String orderStatus,String paymentStatus,
                            String paymentMethod,List<OrderItemDto> items) {
        this.orderId = orderId;
        this.userId = userId;
        this.totalAmount = totalAmount;

        this.discountAmount = discountAmount;
        this.finalAmount = finalAmount;
        this.orderStatus = orderStatus;
        this.paymentStatus = paymentStatus;
        this.paymentMethod = paymentMethod;
        this.items = items;
    }

    public OrderResponseDto(Long orderId, Long userId, BigDecimal totalAmount,
                            BigDecimal discountAmount, BigDecimal finalAmount,
                            String orderStatus,String paymentStatus,
                            String paymentMethod) {
        this.orderId = orderId;
        this.userId = userId;
        this.totalAmount = totalAmount;

        this.discountAmount = discountAmount;
        this.finalAmount = finalAmount;
        this.orderStatus = orderStatus;
        this.paymentStatus = paymentStatus;
        this.paymentMethod = paymentMethod;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public BigDecimal getFinalAmount() {
        return finalAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }
}
