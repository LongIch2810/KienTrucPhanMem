package org.example.order_domain.order_application_service.ports;

import org.example.order_domain.order_application_service.dto.OrderResponseDto;

public interface OrderDetailUseCase {
    OrderResponseDto execute(Long orderId);
}
