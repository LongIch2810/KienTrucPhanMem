package org.example.restaurant_dataaccess.repository;

import org.example.restaurant_dataaccess.entity.RestaurantOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RestaurantOrderRepository extends JpaRepository<RestaurantOrderEntity, UUID> {
    Optional<RestaurantOrderEntity> findByOrderId(UUID orderId);

    List<RestaurantOrderEntity> findAllByRestaurantId(UUID restaurantId);
}
