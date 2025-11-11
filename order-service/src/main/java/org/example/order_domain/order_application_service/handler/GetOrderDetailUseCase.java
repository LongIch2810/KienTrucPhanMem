package org.example.order_domain.order_application_service.handler;

import org.example.order_dataaccess.adapter.OrderRepoGatewayImpl;
import org.example.order_domain.order_application_service.dto.OrderResponseDto;
import org.example.order_domain.order_application_service.mapper.OrderApplicationMapper;
import org.example.order_domain.order_application_service.ports.IOrderDetailUseCase;
import org.example.order_domain.order_domain_core.entity.Order;
import org.example.order_domain.order_domain_core.exception.ResourceNotFoundException;
import org.example.order_domain.order_domain_core.gateway.OrderRepoGateway;
import org.example.order_domain.order_domain_core.valueobject.OrderId;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetOrderDetailUseCase implements IOrderDetailUseCase {
    private final OrderRepoGateway orderRepoGateway;

    public GetOrderDetailUseCase(OrderRepoGatewayImpl orderRepoGateway) {
        this.orderRepoGateway = orderRepoGateway;
    }

    @Override
    public OrderResponseDto execute(UUID orderId) {
        Order order = orderRepoGateway.findById(new OrderId(orderId))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Không tìm thấy đơn hàng"));
        return OrderApplicationMapper.toResponse(order);
    }
}
