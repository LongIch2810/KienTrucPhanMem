package org.example.payment_domain.payment_domain_core.gateway;

import org.example.payment_domain.payment_domain_core.entity.Payment;
import org.example.payment_domain.payment_domain_core.valueobject.OrderId;
import org.example.payment_domain.payment_domain_core.valueobject.PaymentId;

import java.util.Optional;

public interface PaymentRepoGateway {
    Payment save(Payment payment);
    Optional<Payment> findById(PaymentId id);
    Optional<Payment> findByOrderId(OrderId orderId);
}

