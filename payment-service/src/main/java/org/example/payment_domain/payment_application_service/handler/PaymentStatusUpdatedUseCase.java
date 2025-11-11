package org.example.payment_domain.payment_application_service.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.example.payment_domain.payment_application_service.dto.PaymentResponseDto;
import org.example.payment_domain.payment_application_service.mapper.PaymentApplicationMapper;
import org.example.payment_domain.payment_domain_core.exception.PaymentNotFoundException;
import org.example.payment_domain.payment_application_service.port.IPaymentStatusUpdatedUseCase;
import org.example.payment_domain.payment_domain_core.entity.Payment;
import org.example.payment_domain.payment_domain_core.enums.PaymentStatus;
import org.example.payment_domain.payment_domain_core.event.OrderCancelledEvent;
import org.example.payment_domain.payment_domain_core.event.PaymentCompletedEvent;
import org.example.payment_domain.payment_domain_core.event.PaymentFailedEvent;
import org.example.payment_domain.payment_domain_core.gateway.DomainEventPublisher;
import org.example.payment_domain.payment_domain_core.gateway.PaymentRepoGateway;
import org.example.payment_domain.payment_domain_core.valueobject.OrderId;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentStatusUpdatedUseCase implements IPaymentStatusUpdatedUseCase {
    private static final Logger logger = LoggerFactory.getLogger(PaymentStatusUpdatedUseCase.class);
    
    private final PaymentRepoGateway paymentRepoGateway;
    private final DomainEventPublisher domainEventPublisher;

    public PaymentStatusUpdatedUseCase(PaymentRepoGateway paymentRepoGateway, 
                                      DomainEventPublisher domainEventPublisher) {
        this.paymentRepoGateway = paymentRepoGateway;
        this.domainEventPublisher = domainEventPublisher;
    }

    @Override
    public PaymentResponseDto execute(UUID orderId, String status, String failureMessage) {
        OrderId orderIdObj= new OrderId(orderId);
        Optional<Payment> paymentOptional = paymentRepoGateway.findByOrderId(orderIdObj);
        
        if (paymentOptional.isEmpty()) {
            throw new PaymentNotFoundException("Payment not found for orderId: " + orderId);
        }

        Payment payment = paymentOptional.get();
        PaymentStatus paymentStatus = PaymentStatus.fromString(status);

        switch (paymentStatus) {
            case COMPLETED:
                UUID transactionId = UUID.randomUUID();
                payment.markAsCompleted(transactionId);
                payment = paymentRepoGateway.save(payment);
                
                PaymentCompletedEvent completedEvent = new PaymentCompletedEvent(
                    payment.getId().getValue(),
                    payment.getOrderId().getValue(),
                    payment.getCustomerId().getValue(),
                    payment.getRestaurantId().getValue(),
                    payment.getAmount().getAmount(),
                    transactionId,
                    payment.getPaymentStatus(),
                    payment.getPaymentMethod(),
                    payment.getOrderItems(),
                    payment.getUpdatedAt(),
                    payment.getCreatedAt(),
                    payment.getUpdatedAt()
                );
                domainEventPublisher.publish(completedEvent);
                logger.info("[PAYMENT-SERVICE] Published PaymentCompletedEvent for payment: {}", payment.getId().getValue());
                break;
                
            case FAILED:
                String failureReason = "Payment failed";
                payment.markAsFailed(failureReason);
                payment = paymentRepoGateway.save(payment);
                
                PaymentFailedEvent failedEvent = new PaymentFailedEvent(
                    payment.getId().getValue(),
                    payment.getOrderId().getValue(),
                    payment.getCustomerId().getValue(),
                    payment.getAmount().getAmount(),
                    null,
                    payment.getPaymentStatus(),
                    payment.getPaymentMethod(),
                    payment.getOrderItems(),
                    failureReason,
                    payment.getUpdatedAt(),
                    payment.getCreatedAt(),
                    payment.getUpdatedAt()
                );
                domainEventPublisher.publish(failedEvent);
                logger.info("[PAYMENT-SERVICE] Published PaymentFailedEvent for payment: {}", payment.getId().getValue());
                break;
            case REFUNDED:
                payment.markAsRefunded(failureMessage);
                payment = paymentRepoGateway.save(payment);
                OrderCancelledEvent orderCancelledEvent = new OrderCancelledEvent(payment.getOrderId().getValue(), failureMessage);
                domainEventPublisher.publish(orderCancelledEvent);
                logger.info("[PAYMENT-SERVICE] Published OrderCancelledEvent for payment: {}", payment.getId().getValue());
                logger.info("[PAYMENT-SERVICE] Trả lại tiền cho khách hàng ...");
                break;
            default:
                payment.setPaymentStatus(paymentStatus);
                payment = paymentRepoGateway.save(payment);
                break;
        }

        return PaymentApplicationMapper.toPaymentResponseDto(payment);
    }
}

