package org.example.order_domain.order_application_service.handler;

import java.util.Optional;
import java.util.UUID;

import org.example.order_domain.order_application_service.dto.OrderResponseDto;
import org.example.order_domain.order_application_service.mapper.OrderApplicationMapper;
import org.example.order_domain.order_application_service.ports.IOrderUpdateStatusUseCase;
import org.example.order_domain.order_domain_core.entity.Order;
import org.example.order_domain.order_domain_core.exception.ResourceNotFoundException;
import org.example.order_domain.order_domain_core.gateway.OrderRepoGateway;
import org.example.order_domain.order_domain_core.valueobject.OrderId;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UpdateOrderStatusUseCase implements IOrderUpdateStatusUseCase {
    private final OrderRepoGateway orderRepoGateway;

    public UpdateOrderStatusUseCase(OrderRepoGateway orderRepoGateway) {
        this.orderRepoGateway = orderRepoGateway;
    }
    
    @Override
    public OrderResponseDto execute(UUID orderId, String status,String failureMessage){
        Optional<Order> orderOpt = this.orderRepoGateway.findById(new OrderId(orderId));
        if (orderOpt.isEmpty()) {
            throw new ResourceNotFoundException("Order not found");
        }
        Order order = orderOpt.get();
        switch (status) {
            case "PAID":
                order.markPaid();
                break;
            case "APPROVED":
                order.markApproved();
                break;
            case "CANCELLING":
                order.markCancelling();
                break;
            case "CANCELLED":
                order.markCancelled(failureMessage);
                break;
            default:
                throw new IllegalArgumentException("Invalid status: " + status);
        }
        this.orderRepoGateway.save(order);
        return OrderApplicationMapper.toResponse(order);
    }
}
