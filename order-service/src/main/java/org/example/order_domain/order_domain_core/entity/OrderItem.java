package org.example.order_domain.order_domain_core.entity;

import org.example.order_domain.order_domain_core.valieobject.Quantity;

import java.math.BigDecimal;

public class OrderItem {
    private Long orderId;
    private final Long productId;
    private final BigDecimal price;
    private final Quantity quantity;

    public OrderItem(Long productId, Quantity quantity, BigDecimal price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItem(Long orderId,Long productId, Quantity quantity, BigDecimal price) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }


    public Long getOrderId() {
        return orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Quantity getQuantity() {
        return quantity;
    }
}
