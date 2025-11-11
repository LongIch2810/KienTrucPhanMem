package org.example.order_domain.order_application_service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderCreatedDto {

    @NotNull(message = "Customer ID không được null")
    private UUID customerId;

    @NotNull(message = "Restaurant ID không được null")
    private UUID restaurantId;

    @NotNull(message = "Giá không được null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Giá phải > 0")
    private BigDecimal price;

    @NotEmpty(message = "Danh sách sản phẩm không được để trống")
    @Valid
    private List<OrderItemDto> orderItems = new ArrayList<>();

    public OrderCreatedDto() {
    }

    public OrderCreatedDto(
            UUID customerId,
            UUID restaurantId,
            BigDecimal price,
            List<OrderItemDto> orderItems
    ) {
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.price = price;
        this.orderItems = orderItems;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(UUID restaurantId) {
        this.restaurantId = restaurantId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }
}
