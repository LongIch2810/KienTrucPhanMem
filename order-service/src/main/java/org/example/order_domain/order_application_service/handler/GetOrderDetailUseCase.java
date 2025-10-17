package org.example.order_domain.order_application_service.handler;

import org.example.order_dataaccess.adapter.OrderRepoGatewayImpl;
import org.example.order_domain.order_application_service.dto.OrderResponseDto;
import org.example.order_domain.order_application_service.mapper.OrderApplicationMapper;
import org.example.order_domain.order_application_service.ports.OrderDetailUseCase;
import org.example.order_domain.order_domain_core.entity.Order;
import org.example.order_domain.order_domain_core.exception.ResourceNotFoundException;
import org.example.order_domain.order_domain_core.gateway.OrderRepoGateway;
import org.springframework.stereotype.Service;

@Service
public class GetOrderDetailUseCase implements OrderDetailUseCase {
    private final OrderRepoGateway orderRepoGateway;

    public GetOrderDetailUseCase(OrderRepoGatewayImpl orderRepoGateway) {
        this.orderRepoGateway = orderRepoGateway;
    }

    @Override
    public OrderResponseDto execute(Long orderId) {
        Order order = orderRepoGateway.findById(orderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Không tìm thấy đơn hàng"));
        return OrderApplicationMapper.toResponse(order);
    }
}
