package org.example.restaurant_domain.restaurant_domain_core.gateway;

import org.example.restaurant_domain.restaurant_domain_core.entity.MenuItem;
import org.example.restaurant_domain.restaurant_domain_core.valueobject.MenuItemId;
import org.example.restaurant_domain.restaurant_domain_core.valueobject.RestaurantId;

import java.util.List;
import java.util.Optional;

public interface MenuItemRepoGateway {
    MenuItem save(MenuItem menuItem);
    Optional<MenuItem> findById(MenuItemId menuItemId);
    List<MenuItem> findAllByRestaurantId(RestaurantId restaurantId);
    void deleteById(MenuItemId menuItemId);
    boolean existsById(MenuItemId menuItemId);
}