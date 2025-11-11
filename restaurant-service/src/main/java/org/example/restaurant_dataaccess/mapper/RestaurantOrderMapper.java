package org.example.restaurant_dataaccess.mapper;

import org.example.restaurant_dataaccess.entity.RestaurantOrderEntity;
import org.example.restaurant_domain.restaurant_domain_core.entity.RestaurantOrder;
import org.example.restaurant_domain.restaurant_domain_core.valueobject.Money;
import org.example.restaurant_domain.restaurant_domain_core.valueobject.OrderId;
import org.example.restaurant_domain.restaurant_domain_core.valueobject.RestaurantId;
import org.example.restaurant_domain.restaurant_domain_core.valueobject.RestaurantOrderId;

public class RestaurantOrderMapper {
    private RestaurantOrderMapper() {
    }

    public static RestaurantOrder toDomain(RestaurantOrderEntity entity) {
        return new RestaurantOrder(
            new RestaurantOrderId(entity.getId()),
            new OrderId(entity.getOrderId()),
            new RestaurantId(entity.getRestaurantId()),
            new Money(entity.getTotalAmount()),
            entity.getApprovalStatus(),
            entity.getRejectionReason(),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }

    public static RestaurantOrderEntity toEntity(RestaurantOrder domain) {
        return new RestaurantOrderEntity(
            domain.getOrderId().getValue(),
            domain.getRestaurantId().getValue(),
            domain.getTotalAmount().getAmount(),
            domain.getApprovalStatus(),
            domain.getRejectionReason(),
            domain.getCreatedAt()
        );
    }
}
