package org.example.order_domain.order_application_service.ports;

import org.example.order_domain.order_application_service.dto.OrderResponseDto;

import java.util.UUID;

public interface IOrderDetailUseCase {
    OrderResponseDto execute(UUID orderId);
}
