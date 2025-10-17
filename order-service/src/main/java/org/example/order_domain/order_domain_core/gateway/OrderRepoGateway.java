package org.example.order_domain.order_domain_core.gateway;

import org.example.order_domain.order_domain_core.entity.Order;

import java.util.Optional;

public interface OrderRepoGateway {
    Order save(Order order);
    Optional<Order> findById(Long id);
}
