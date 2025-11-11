package org.example.payment_dataaccess.adapter;

import org.example.payment_dataaccess.entity.PaymentEntity;
import org.example.payment_dataaccess.mapper.PaymentMapper;
import org.example.payment_dataaccess.repository.PaymentRepository;
import org.example.payment_domain.payment_domain_core.entity.Payment;
import org.example.payment_domain.payment_domain_core.gateway.PaymentRepoGateway;
import org.example.payment_domain.payment_domain_core.valueobject.OrderId;
import org.example.payment_domain.payment_domain_core.valueobject.PaymentId;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PaymentRepoGatewayImpl implements PaymentRepoGateway {
    private final PaymentRepository paymentRepository;

    public PaymentRepoGatewayImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment save(Payment payment) {
        PaymentEntity paymentEntity = PaymentMapper.toPaymentEntity(payment);
        paymentEntity = paymentRepository.save(paymentEntity);
        return PaymentMapper.toPaymentDomain(paymentEntity);
    }

    @Override
    public Optional<Payment> findById(PaymentId id) {
        Optional<PaymentEntity> paymentEntity = paymentRepository.findById(id.getValue());
        return paymentEntity.map(PaymentMapper::toPaymentDomain);
    }

    @Override
    public Optional<Payment> findByOrderId(OrderId orderId) {
        Optional<PaymentEntity> paymentEntity = paymentRepository.findByOrderId(orderId.getValue());
        return paymentEntity.map(PaymentMapper::toPaymentDomain);
    }
}

