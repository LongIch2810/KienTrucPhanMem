package org.example.restaurant_dataaccess.mapper;

import org.example.restaurant_dataaccess.entity.MenuItemEntity;
import org.example.restaurant_domain.restaurant_domain_core.entity.MenuItem;
import org.example.restaurant_domain.restaurant_domain_core.valueobject.MenuItemId;
import org.example.restaurant_domain.restaurant_domain_core.valueobject.Money;
import org.example.restaurant_domain.restaurant_domain_core.valueobject.Quantity;
import org.example.restaurant_domain.restaurant_domain_core.valueobject.RestaurantId;

public class MenuItemMapper {
    private MenuItemMapper() {
        // Private constructor to prevent instantiation
    }
    public static MenuItem toDomain(MenuItemEntity entity) {
        return new MenuItem(
            new MenuItemId(entity.getId()),
            new RestaurantId(entity.getRestaurantId()),
            entity.getName(),
            new Money(entity.getPrice()),
            new Quantity(entity.getQuantity()),
            entity.isAvailable()
        );
    }

    public static MenuItemEntity toEntity(MenuItem domain) {
        MenuItemEntity entity = new MenuItemEntity();
        entity.setId(domain.getId().getValue());
        entity.setRestaurantId(domain.getRestaurantId().getValue());
        entity.setName(domain.getName());
        entity.setPrice(domain.getPrice().getAmount());
        entity.setQuantity(domain.getQuantity().getValue());
        entity.setAvailable(domain.isAvailable());
        return entity;
    }
}
