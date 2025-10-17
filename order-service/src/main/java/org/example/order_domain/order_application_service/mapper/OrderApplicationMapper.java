package org.example.order_domain.order_application_service.mapper;

import org.example.order_domain.order_application_service.dto.OrderCreatedDto;
import org.example.order_domain.order_application_service.dto.OrderItemDto;
import org.example.order_domain.order_application_service.dto.OrderResponseDto;
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

public class OrderApplicationMapper {
    static public Order toDomain(OrderCreatedDto orderCreatedDto) {
        Order order = new Order(orderCreatedDto.getUserId(),
                new Money(orderCreatedDto.getTotalAmount(), Currency.getInstance(orderCreatedDto.getCurrency())),
                new Money(orderCreatedDto.getDiscountAmount(),Currency.getInstance(orderCreatedDto.getCurrency())),
                new Money(orderCreatedDto.getFinalAmount(),Currency.getInstance(orderCreatedDto.getCurrency())),
                OrderStatus.PENDING, PaymentStatus.PENDING,
                PaymentMethod.fromString(orderCreatedDto.getPaymentMethod())
               );
        List<OrderItem> orderItems = orderCreatedDto.getOrderItems().stream()
                .map(item -> new OrderItem(item.getProductId(),
                        new Quantity(item.getQuantity()),item.getPrice()))
                .collect(Collectors.toList());

        order.setOrderItems(orderItems);

        return order;
    }

    static public OrderResponseDto toResponse(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto(order.getId(),
                order.getUserId(),order.getTotalAmount().getAmount(),
                order.getDiscountAmount().getAmount(),
                order.getFinalAmount().getAmount(),order.getOrderStatus().getValue(),
                order.getPaymentStatus().getValue(),order.getPaymentMethod().getValue());
        List<OrderItemDto> items = order.getOrderItems().stream()
                .map(item -> new OrderItemDto(item.getProductId(),
                        item.getQuantity().getValue(),item.getPrice()))
                .collect(Collectors.toList());
        orderResponseDto.setItems(items);
        return orderResponseDto;
    }
}
