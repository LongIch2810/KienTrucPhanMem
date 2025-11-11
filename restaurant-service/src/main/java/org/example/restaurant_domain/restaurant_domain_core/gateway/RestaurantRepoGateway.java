package org.example.restaurant_domain.restaurant_domain_core.gateway;

import org.example.restaurant_domain.restaurant_domain_core.entity.Restaurant;
import org.example.restaurant_domain.restaurant_domain_core.enums.RestaurantStatus;
import org.example.restaurant_domain.restaurant_domain_core.valueobject.RestaurantId;

import java.util.Optional;
import java.util.UUID;

public interface RestaurantRepoGateway {
    Restaurant save(Restaurant restaurant);

    Optional<Restaurant> findById(RestaurantId restaurantId);

    Optional<Restaurant> findByOwnerId(UUID ownerId);

    void deleteById(RestaurantId restaurantId);

    boolean existsById(RestaurantId restaurantId);

    Optional<Restaurant> findByIdAndStatus(RestaurantId restaurantId, RestaurantStatus status);
}