package org.example.restaurant_dataaccess.repository;

import org.example.restaurant_dataaccess.entity.RestaurantEntity;
import org.example.restaurant_domain.restaurant_domain_core.enums.RestaurantStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, UUID> {
    Optional<RestaurantEntity> findByOwnerId(UUID ownerId);

    Optional<RestaurantEntity> findByIdAndStatus(UUID id, RestaurantStatus status);
}
