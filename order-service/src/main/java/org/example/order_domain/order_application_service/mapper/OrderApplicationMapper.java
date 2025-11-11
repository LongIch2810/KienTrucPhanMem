package org.example.order_domain.order_application_service.mapper;

import org.example.order_domain.order_application_service.dto.OrderCreatedDto;
import org.example.order_domain.order_application_service.dto.OrderItemDto;
import org.example.order_domain.order_application_service.dto.OrderResponseDto;
import org.example.order_domain.order_domain_core.entity.Order;
import org.example.order_domain.order_domain_core.entity.OrderItem;
import org.example.order_domain.order_domain_core.enums.OrderStatus;
import org.example.order_domain.order_domain_core.valueobject.CustomerId;
import org.example.order_domain.order_domain_core.valueobject.Money;
import org.example.order_domain.order_domain_core.valueobject.ProductId;
import org.example.order_domain.order_domain_core.valueobject.Quantity;
import org.example.order_domain.order_domain_core.valueobject.RestaurantId;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderApplicationMapper {
    static public Order toDomain(OrderCreatedDto orderCreatedDto) {
        // Generate tracking ID
        UUID trackingId = UUID.randomUUID();
        
        // Get IDs from DTO
        UUID customerUuid = orderCreatedDto.getCustomerId();
        UUID restaurantUuid = orderCreatedDto.getRestaurantId();
        
        // Get price from DTO
        Money price = new Money(orderCreatedDto.getPrice());
        
        // Create order
        Order order = new Order(
                new CustomerId(customerUuid),
                new RestaurantId(restaurantUuid),
                trackingId,
                price,
                OrderStatus.PENDING,
                null, // failureMessages - null initially
                null  // orderItems - will be set below
        );
        
        // Map order items
        List<OrderItem> orderItems = orderCreatedDto.getOrderItems().stream()
                .map(item -> new OrderItem(
                        new ProductId(item.getProductId()),
                        new Quantity(item.getQuantity()),
                        new Money(item.getPrice())
                ))
                .collect(Collectors.toList());

        order.setOrderItems(orderItems);

        return order;
    }

    static public OrderResponseDto toResponse(Order order) {
        // Map UUIDs directly from domain value objects
        UUID orderId = order.getId() != null ? order.getId().getValue() : null;
        UUID customerId = order.getCustomerId() != null ? order.getCustomerId().getValue() : null;
        UUID restaurantId = order.getRestaurantId() != null ? order.getRestaurantId().getValue() : null;
        UUID trackingId = order.getTrackingId();
        
        // Map price
        java.math.BigDecimal priceAmount = order.getPrice() != null ? 
                order.getPrice().getAmount() : java.math.BigDecimal.ZERO;
        
        // Map order status
        String orderStatus = order.getOrderStatus() != null ? 
                order.getOrderStatus().getValue() : "PENDING";
        
        // Map failure messages
        String failureMessages = order.getFailureMessages();
        
        // Get createdAt from domain Order
        java.time.LocalDateTime createdAt = order.getCreatedAt();
        
        // Map order items
        List<OrderItemDto> items = order.getOrderItems().stream()
                .map(item -> {
                    UUID productUuid = item.getProductId() != null ? 
                            item.getProductId().getValue() : null;
                    java.math.BigDecimal price = item.getPrice() != null ? 
                            item.getPrice().getAmount() : java.math.BigDecimal.ZERO;
                    java.math.BigDecimal subTotal = item.getSubTotal() != null ? 
                            item.getSubTotal().getAmount() : java.math.BigDecimal.ZERO;
                    Integer quantity = item.getQuantity() != null ? 
                            item.getQuantity().getValue() : 0;
                    
                    return new OrderItemDto(productUuid, quantity, price, subTotal);
                })
                .collect(Collectors.toList());
        
        OrderResponseDto orderResponseDto = new OrderResponseDto(
                orderId,
                customerId,
                restaurantId,
                trackingId,
                priceAmount,
                orderStatus,
                failureMessages,
                createdAt,
                items
        );
        
        return orderResponseDto;
    }
}
