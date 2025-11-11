package org.example.payment_dataaccess.mapper;

import org.example.payment_dataaccess.entity.PaymentEntity;
import org.example.payment_domain.payment_domain_core.entity.Payment;
import org.example.payment_domain.payment_domain_core.valueobject.CustomerId;
import org.example.payment_domain.payment_domain_core.valueobject.Money;
import org.example.payment_domain.payment_domain_core.valueobject.OrderId;
import org.example.payment_domain.payment_domain_core.valueobject.PaymentId;
import org.example.payment_domain.payment_domain_core.valueobject.RestaurantId;

public class PaymentMapper {

    public static Payment toPaymentDomain(PaymentEntity entity) {
        if (entity == null)
            return null;

        return new Payment(
                entity.getId() != null ? new PaymentId(entity.getId()) : null,
                new OrderId(entity.getOrderId()),
                new CustomerId(entity.getCustomerId()),
                new RestaurantId(entity.getRestaurantId()),
                new Money(entity.getAmount()),
                entity.getPaymentMethod(),
                entity.getPaymentStatus(),
                entity.getOrderItems(),
                entity.getTransactionId(),
                entity.getFailureReason(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }

    public static PaymentEntity toPaymentEntity(Payment domain) {
        if (domain == null)
            return null;

        PaymentEntity entity = PaymentEntity.createPayment(
                domain.getOrderId().getValue(),
                domain.getCustomerId().getValue(),
                domain.getRestaurantId().getValue(),
                domain.getAmount().getAmount(),
                domain.getPaymentMethod(),
                domain.getOrderItems());

        if (domain.getId() != null) {
            entity.setId(domain.getId().getValue());
        }
        if (domain.getPaymentStatus() != null) {
            entity.setPaymentStatus(domain.getPaymentStatus());
        }
        if (domain.getTransactionId() != null) {
            entity.setTransactionId(domain.getTransactionId());
        }
        if (domain.getFailureReason() != null) {
            entity.setFailureReason(domain.getFailureReason());
        }

        if (domain.getCreatedAt() != null) {
            entity.setCreatedAt(domain.getCreatedAt());
        }
        if (domain.getUpdatedAt() != null) {
            entity.setUpdatedAt(domain.getUpdatedAt());
        }

        return entity;
    }
}
