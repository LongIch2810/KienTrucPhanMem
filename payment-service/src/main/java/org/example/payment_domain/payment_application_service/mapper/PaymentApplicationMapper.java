package org.example.payment_domain.payment_application_service.mapper;

import org.example.payment_domain.payment_application_service.dto.PaymentCreatedDto;
import org.example.payment_domain.payment_application_service.dto.PaymentResponseDto;
import org.example.payment_domain.payment_domain_core.entity.OrderItem;
import org.example.payment_domain.payment_domain_core.entity.Payment;
import org.example.payment_domain.payment_domain_core.enums.PaymentMethod;
import org.example.payment_domain.payment_domain_core.valueobject.*;
import java.util.List;

import java.math.BigDecimal;

public class PaymentApplicationMapper {
    private PaymentApplicationMapper() {
        // Private constructor to prevent instantiation
    }
    public static Payment toPayment(PaymentCreatedDto paymentCreatedDto) {
        if (paymentCreatedDto == null) {
            return null;
        }
        
        OrderId orderId = paymentCreatedDto.getOrderId() != null 
            ? new OrderId(paymentCreatedDto.getOrderId()) 
            : null;
        
        CustomerId customerId = paymentCreatedDto.getCustomerId() != null 
            ? new CustomerId(paymentCreatedDto.getCustomerId()) 
            : null;
        
        RestaurantId restaurantId = paymentCreatedDto.getRestaurantId() != null 
            ? new RestaurantId(paymentCreatedDto.getRestaurantId()) 
            : null;
        
        Money amount = paymentCreatedDto.getAmount() != null 
            ? new Money(paymentCreatedDto.getAmount()) 
            : Money.ZERO;
        
        PaymentMethod paymentMethod = paymentCreatedDto.getPaymentMethod() != null 
            ? PaymentMethod.fromString(paymentCreatedDto.getPaymentMethod()) 
            : PaymentMethod.COD;
        
        List<OrderItem> orderItems = paymentCreatedDto.getOrderItems();
        
        return new Payment(orderId, customerId, restaurantId, amount, paymentMethod, orderItems);
    }

    public static PaymentCreatedDto toPaymentCreatedDto(Payment payment) {
        if (payment == null) {
            return null;
        }
        
        PaymentCreatedDto dto = new PaymentCreatedDto();
        dto.setOrderId(payment.getOrderId() != null ? payment.getOrderId().getValue() : null);
        dto.setCustomerId(payment.getCustomerId() != null ? payment.getCustomerId().getValue() : null);
        dto.setRestaurantId(payment.getRestaurantId() != null ? payment.getRestaurantId().getValue() : null);
        dto.setAmount(payment.getAmount() != null ? payment.getAmount().getAmount() : BigDecimal.ZERO);
        dto.setPaymentMethod(payment.getPaymentMethod() != null ? payment.getPaymentMethod().getValue() : null);
        dto.setOrderItems(payment.getOrderItems());
        
        return dto;
    }

    public static PaymentResponseDto toPaymentResponseDto(Payment payment) {
        if (payment == null) {
            return null;
        }
        
        PaymentResponseDto dto = new PaymentResponseDto();
        dto.setId(payment.getId() != null ? payment.getId().getValue() : null);
        dto.setOrderId(payment.getOrderId() != null ? payment.getOrderId().getValue() : null);
        dto.setCustomerId(payment.getCustomerId() != null ? payment.getCustomerId().getValue() : null);
        dto.setRestaurantId(payment.getRestaurantId() != null ? payment.getRestaurantId().getValue() : null);
        dto.setAmount(payment.getAmount() != null ? payment.getAmount().getAmount() : BigDecimal.ZERO);
        dto.setPaymentMethod(payment.getPaymentMethod() != null ? payment.getPaymentMethod().getValue() : null);
        dto.setPaymentStatus(payment.getPaymentStatus() != null ? payment.getPaymentStatus().getValue() : null);
        dto.setOrderItems(payment.getOrderItems());
        dto.setTransactionId(payment.getTransactionId());
        dto.setFailureReason(payment.getFailureReason());
        dto.setCreatedAt(payment.getCreatedAt());
        dto.setUpdatedAt(payment.getUpdatedAt());
        
        return dto;
    }

}
