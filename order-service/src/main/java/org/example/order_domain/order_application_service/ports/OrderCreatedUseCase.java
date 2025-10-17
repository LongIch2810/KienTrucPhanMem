package org.example.order_domain.order_application_service.ports;

import org.example.order_domain.order_application_service.dto.OrderCreatedDto;
import org.example.order_domain.order_application_service.dto.OrderResponseDto;

public interface OrderCreatedUseCase {
    OrderResponseDto execute(OrderCreatedDto orderCreatedDto);
}
