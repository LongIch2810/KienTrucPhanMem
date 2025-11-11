package org.example.order_dataaccess.mapper;

import org.example.order_dataaccess.entity.OrderEntity;
import org.example.order_dataaccess.entity.OrderItemEntity;
import org.example.order_domain.order_domain_core.entity.Order;
import org.example.order_domain.order_domain_core.entity.OrderItem;
import org.example.order_domain.order_domain_core.valueobject.CustomerId;
import org.example.order_domain.order_domain_core.valueobject.Money;
import org.example.order_domain.order_domain_core.valueobject.OrderId;
import org.example.order_domain.order_domain_core.valueobject.ProductId;
import org.example.order_domain.order_domain_core.valueobject.Quantity;
import org.example.order_domain.order_domain_core.valueobject.RestaurantId;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {
    static public Order toOrderDomain(OrderEntity entity) {
        if (entity == null) return null;
        List<OrderItem> orderItems = entity.getOrderItemEntities().stream()
                .map(itemEntity -> new OrderItem(
                        new OrderId(entity.getId()),
                        new ProductId(itemEntity.getProductId()),
                        new Quantity(itemEntity.getQuantity()),
                        new Money(itemEntity.getPrice())
                ))
                .collect(Collectors.toList());
        return new Order(
                entity.getId() != null ? new OrderId(entity.getId()) : null,
                new CustomerId(entity.getCustomerId()),
                new RestaurantId(entity.getRestaurantId()),
                entity.getTrackingId(),
                new Money(entity.getPrice()),
                entity.getOrderStatus(),
                entity.getFailureMessages(),
                entity.getCreatedAt(),
                orderItems
                );
    }

    static public OrderEntity toOrderEntity(Order domain) {
        if (domain == null) return null;
        OrderEntity orderEntity = new OrderEntity(
                domain.getId() != null ? domain.getId().getValue() : null,
                domain.getCustomerId().getValue(),
                domain.getRestaurantId().getValue(),
                domain.getTrackingId(),
                domain.getPrice().getAmount(),
                domain.getOrderStatus(),
                domain.getFailureMessages()
        );
        
        // Set createdAt from domain if available
        if (domain.getCreatedAt() != null) {
            orderEntity.setCreatedAt(domain.getCreatedAt());
        }
        
        List<OrderItemEntity> itemEntities = domain.getOrderItems().stream().map(item -> {
            OrderItemEntity orderItemEntity = new OrderItemEntity(
                    orderEntity,
                    item.getProductId().getValue(),
                    item.getQuantity().getValue(),
                    item.getPrice().getAmount()
            );
            return orderItemEntity;
        }).collect(Collectors.toList());

        orderEntity.setOrderItemEntities(itemEntities);
        return orderEntity;
    }
}
