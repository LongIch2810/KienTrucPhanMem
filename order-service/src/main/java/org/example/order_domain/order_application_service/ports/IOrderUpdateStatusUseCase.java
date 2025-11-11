package org.example.order_domain.order_application_service.ports;

import java.util.UUID;

import org.example.order_domain.order_application_service.dto.OrderResponseDto;

public interface IOrderUpdateStatusUseCase {
    OrderResponseDto execute(UUID orderId, String status, String failureMessage);
}
