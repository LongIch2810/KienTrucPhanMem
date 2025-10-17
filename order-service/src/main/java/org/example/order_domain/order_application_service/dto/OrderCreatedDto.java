package org.example.order_domain.order_application_service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderCreatedDto {

    @NotNull(message = "User ID không được null")
    private Long userId;

    @NotNull(message = "Tổng tiền không được null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Tổng tiền phải > 0")
    private BigDecimal totalAmount;

    @NotNull(message = "Số tiền giảm giá không được null")
    @DecimalMin(value = "0.0", inclusive = true, message = "Số tiền giảm giá phải >= 0")
    private BigDecimal discountAmount;

    @NotNull(message = "Số tiền cuối cùng không được null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Số tiền cuối cùng phải > 0")
    private BigDecimal finalAmount;

    @NotBlank(message = "Đơn vị tiền tệ không được để trống")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Đơn vị tiền tệ phải là mã ISO 3 ký tự (VD: USD, VND)")
    private String currency;


    @NotBlank(message = "Phương thức thanh toán không được để trống")
    private String paymentMethod;

    @NotEmpty(message = "Danh sách sản phẩm không được để trống")
    @Valid
    private List<OrderItemDto> orderItems = new ArrayList<>();

    public OrderCreatedDto() {
    }

    public OrderCreatedDto(
            Long userId,
            BigDecimal totalAmount,
            BigDecimal discountAmount,
            BigDecimal finalAmount,
            String currency,
            String paymentMethod,
            List<OrderItemDto> orderItems
    ) {
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        this.finalAmount = finalAmount;
        this.currency = currency;
        this.paymentMethod = paymentMethod;
        this.orderItems = orderItems;
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

    public String getCurrency() {
        return currency;
    }


    public String getPaymentMethod() {
        return paymentMethod;
    }

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }
}
