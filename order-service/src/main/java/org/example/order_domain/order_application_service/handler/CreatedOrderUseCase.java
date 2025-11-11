package org.example.order_domain.order_application_service.handler;

import org.example.order_domain.order_application_service.dto.OrderCreatedDto;
import org.example.order_domain.order_application_service.dto.OrderResponseDto;
import org.example.order_domain.order_application_service.mapper.OrderApplicationMapper;
import org.example.order_domain.order_application_service.ports.IOrderCreatedUseCase;
import org.example.order_domain.order_domain_core.entity.Order;
import org.example.order_domain.order_domain_core.gateway.DomainEventPublisher;
import org.example.order_domain.order_domain_core.gateway.OrderRepoGateway;
import org.springframework.stereotype.Service;

@Service
public class CreatedOrderUseCase implements IOrderCreatedUseCase {
    private final OrderRepoGateway orderRepoGateway;
    private final DomainEventPublisher domainEventPublisher;
    public CreatedOrderUseCase(OrderRepoGateway orderRepoGateway,
                               DomainEventPublisher domainEventPublisher) {
        this.orderRepoGateway = orderRepoGateway;
        this.domainEventPublisher = domainEventPublisher;
    }

    @Override
    public OrderResponseDto execute(OrderCreatedDto orderCreatedDto) {
        Order order = OrderApplicationMapper.toDomain(orderCreatedDto);
        Order newOrder = orderRepoGateway.save(order);
        newOrder.markCreated();
        newOrder.getDomainEvents().forEach(domainEventPublisher::publish);
        return OrderApplicationMapper.toResponse(newOrder);
    }
}
