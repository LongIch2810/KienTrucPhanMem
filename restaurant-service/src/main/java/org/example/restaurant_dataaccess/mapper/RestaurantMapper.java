package org.example.restaurant_dataaccess.mapper;

import org.example.restaurant_dataaccess.entity.RestaurantEntity;
import org.example.restaurant_domain.restaurant_domain_core.entity.Restaurant;

public class RestaurantMapper {
    private RestaurantMapper() {
        // Private constructor to prevent instantiation
    }

    public static Restaurant toDomain(RestaurantEntity entity) {
        return new Restaurant(
            entity.getId(),
            entity.getName(),
            entity.getOwnerId(),
            entity.getStatus(),
            entity.getCreatedAt()
        );
    }

    public static RestaurantEntity toEntity(Restaurant domain) {
        RestaurantEntity entity = new RestaurantEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setOwnerId(domain.getOwnerId());
        entity.setStatus(domain.getStatus());
        entity.setCreatedAt(domain.getCreatedAt());
        return entity;
    }
}
