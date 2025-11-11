package org.example.payment_domain.payment_application_service.port;

import org.example.payment_domain.payment_application_service.dto.PaymentCreatedDto;
import org.example.payment_domain.payment_application_service.dto.PaymentResponseDto;

public interface IPaymentCreatedUseCase {
    PaymentResponseDto execute(PaymentCreatedDto paymentCreatedDto);
}
