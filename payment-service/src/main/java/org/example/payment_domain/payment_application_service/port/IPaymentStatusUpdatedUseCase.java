package org.example.payment_domain.payment_application_service.port;

import org.example.payment_domain.payment_application_service.dto.PaymentResponseDto;

import java.util.UUID;

public interface IPaymentStatusUpdatedUseCase {
    PaymentResponseDto execute(UUID orderId, String status, String failureMessage);
}

