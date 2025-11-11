package org.example.restaurant_domain.restaurant_domain_core.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderItem {
    private UUID productId;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subtotal;

    public OrderItem() {
    }

    public OrderItem(UUID productId, Integer quantity, BigDecimal price, BigDecimal subtotal) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.subtotal = subtotal;
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

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                ", price=" + price +
                ", subtotal=" + subtotal +
                '}';
    }
}