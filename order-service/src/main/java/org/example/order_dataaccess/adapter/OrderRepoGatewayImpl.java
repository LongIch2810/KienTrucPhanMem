package org.example.order_dataaccess.adapter;

import org.example.order_dataaccess.entity.OrderEntity;
import org.example.order_dataaccess.mapper.OrderMapper;
import org.example.order_dataaccess.repository.OrderRepository;
import org.example.order_domain.order_domain_core.entity.Order;
import org.example.order_domain.order_domain_core.gateway.OrderRepoGateway;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OrderRepoGatewayImpl implements OrderRepoGateway {
    private final OrderRepository orderRepository;
    public OrderRepoGatewayImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = OrderMapper.toOrderEntity(order);
        orderEntity = orderRepository.save(orderEntity);
        return OrderMapper.toOrderDomain(orderEntity);
    }

    @Override
    public Optional<Order> findById(Long id) {
        Optional<OrderEntity> orderEntity = orderRepository.findById(id);
        return orderEntity.map(OrderMapper::toOrderDomain);
    }
}
