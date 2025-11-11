package org.example.payment_domain.payment_domain_core.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.UUID;

public class OrderItem {
    private UUID productId;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subTotal;

    public OrderItem() {
    }

    @JsonCreator
    public OrderItem(
            @JsonProperty("productId") UUID productId,
            @JsonProperty("quantity") Integer quantity,
            @JsonProperty("price") BigDecimal price,
            @JsonProperty("subTotal") BigDecimal subTotal) {
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

    @Override
    public String toString() {
        return "OrderItem{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                ", price=" + price +
                ", subTotal=" + subTotal +
                '}';
    }
}
