package org.example.order_dataaccess.mapper;

import org.example.order_dataaccess.entity.OrderEntity;
import org.example.order_dataaccess.entity.OrderItemEntity;
import org.example.order_domain.order_domain_core.entity.Order;
import org.example.order_domain.order_domain_core.entity.OrderItem;
import org.example.order_domain.order_domain_core.enums.OrderStatus;
import org.example.order_domain.order_domain_core.enums.PaymentMethod;
import org.example.order_domain.order_domain_core.enums.PaymentStatus;
import org.example.order_domain.order_domain_core.valieobject.Money;
import org.example.order_domain.order_domain_core.valieobject.Quantity;

import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {
    static public Order toOrderDomain(OrderEntity entity) {
        if (entity == null) return null;
        List<OrderItem> orderItems = entity.getOrderItemEntities().stream()
                .map(itemEntity -> new OrderItem(
                        itemEntity.getId(), // Giả định OrderItem domain cũng có ID
                        itemEntity.getProductId(),
                        new Quantity(itemEntity.getQuantity()),
                        itemEntity.getPrice()
                ))
                .collect(Collectors.toList());
        return new Order(
                entity.getId(),
                entity.getUserId(),
                new Money(entity.getTotalAmount(), Currency.getInstance(entity.getCurrency())),
                new Money(entity.getDiscountAmount(), Currency.getInstance(entity.getCurrency())),
                new Money(entity.getFinalAmount(), Currency.getInstance(entity.getCurrency())),
                entity.getOrderStatus(),
                entity.getPaymentStatus(),
                entity.getPaymentMethod(),
                orderItems
                );
    }

    static public OrderEntity toOrderEntity(Order domain) {
        if (domain == null) return null;
        OrderEntity orderEntity = new OrderEntity(
                domain.getUserId(),
                domain.getTotalAmount().getAmount(),
                domain.getDiscountAmount().getAmount(),
                domain.getFinalAmount().getAmount(),
                domain.getTotalAmount().getCurrency().getCurrencyCode(),
                domain.getOrderStatus(),
                domain.getPaymentStatus(),
                domain.getPaymentMethod()

        );
        List<OrderItemEntity> itemEntities = domain.getOrderItems().stream().map(item -> {
            OrderItemEntity orderItemEntity = new OrderItemEntity(
                    item.getProductId(), item.getQuantity().getValue(), item.getPrice()
            );

            orderItemEntity.setOrder(orderEntity);
            return orderItemEntity;
        }).collect(Collectors.toList());

        orderEntity.setOrderItemEntities(itemEntities);
        return orderEntity;
    }
}
