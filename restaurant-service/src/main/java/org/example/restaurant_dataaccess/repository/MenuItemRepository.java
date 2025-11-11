package org.example.restaurant_dataaccess.repository;

import org.example.restaurant_dataaccess.entity.MenuItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MenuItemRepository extends JpaRepository<MenuItemEntity, UUID> {
    List<MenuItemEntity>findAllByRestaurantId(UUID restaurantId);
}
