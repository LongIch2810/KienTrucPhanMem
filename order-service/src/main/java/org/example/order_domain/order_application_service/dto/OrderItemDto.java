package org.example.order_domain.order_application_service.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderItemDto {
    // For creating orders - using UUID directly
    @NotNull(message = "Product ID không được null")
    private UUID productId;

    @NotNull(message = "Số lượng không được null")
    @Min(value = 1, message = "Số lượng phải >= 1")
    private Integer quantity;

    @NotNull(message = "Giá không được null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Giá phải > 0")
    private BigDecimal price;

    // For response DTOs
    private BigDecimal subTotal;

    public OrderItemDto() {}

    // Constructor for creating orders
    public OrderItemDto(UUID productId, Integer quantity, BigDecimal price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    // Constructor for response (includes subTotal)
    public OrderItemDto(UUID productId, Integer quantity, BigDecimal price, BigDecimal subTotal) {
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
