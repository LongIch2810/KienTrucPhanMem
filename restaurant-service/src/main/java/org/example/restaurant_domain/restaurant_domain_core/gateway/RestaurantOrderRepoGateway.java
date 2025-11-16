package org.example.restaurant_domain.restaurant_domain_core.gateway;

import org.example.restaurant_domain.restaurant_domain_core.entity.RestaurantOrder;
import org.example.restaurant_domain.restaurant_domain_core.valueobject.OrderId;
import org.example.restaurant_domain.restaurant_domain_core.valueobject.RestaurantId;
import org.example.restaurant_domain.restaurant_domain_core.valueobject.RestaurantOrderId;

import java.util.List;
import java.util.Optional;

public interface RestaurantOrderRepoGateway {
    RestaurantOrder save(RestaurantOrder restaurantOrder);

    Optional<RestaurantOrder> findById(RestaurantOrderId restaurantOrderId);

    Optional<RestaurantOrder> findByOrderId(OrderId orderId);

    List<RestaurantOrder> findAllByRestaurantId(RestaurantId restaurantId);

    boolean existsById(RestaurantOrderId restaurantOrderId);

    void update(RestaurantOrder restaurantOrder);
}