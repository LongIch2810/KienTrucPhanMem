package org.example.order_domain.order_application_service.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


import java.math.BigDecimal;

public class OrderItemDto {
    @NotNull(message = "Product ID không được null")
    private Long productId;

    @NotNull(message = "Số lượng không được null")
    @Min(value = 1, message = "Số lượng phải >= 1")
    private Integer quantity;

    @NotNull(message = "Giá không được null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Giá phải > 0")
    private BigDecimal price;

    public OrderItemDto() {}

    public OrderItemDto(Long productId, Integer quantity, BigDecimal price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }


    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
