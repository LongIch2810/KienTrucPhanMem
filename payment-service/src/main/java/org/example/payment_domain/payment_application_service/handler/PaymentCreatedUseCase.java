package org.example.payment_domain.payment_application_service.handler;

import org.example.payment_domain.payment_application_service.dto.PaymentCreatedDto;
import org.example.payment_domain.payment_application_service.dto.PaymentResponseDto;
import org.example.payment_domain.payment_application_service.mapper.PaymentApplicationMapper;
import org.example.payment_domain.payment_application_service.port.IPaymentCreatedUseCase;
import org.example.payment_domain.payment_domain_core.entity.Payment;
import org.example.payment_domain.payment_domain_core.gateway.PaymentRepoGateway;
import org.springframework.stereotype.Service;

@Service
public class PaymentCreatedUseCase implements IPaymentCreatedUseCase {
    private final PaymentRepoGateway paymentRepoGateway;

    public PaymentCreatedUseCase(PaymentRepoGateway paymentRepoGateway) {
        this.paymentRepoGateway = paymentRepoGateway;
    }
    public PaymentResponseDto execute(PaymentCreatedDto paymentCreatedDto) {
        Payment payment = PaymentApplicationMapper.toPayment(paymentCreatedDto);
        payment = this.paymentRepoGateway.save(payment);
        return PaymentApplicationMapper.toPaymentResponseDto(payment);
    }
}
