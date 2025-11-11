package org.example.restaurant_dataaccess.adapter;

import org.example.restaurant_dataaccess.entity.RestaurantEntity;
import org.example.restaurant_dataaccess.mapper.RestaurantMapper;
import org.example.restaurant_dataaccess.repository.RestaurantRepository;
import org.example.restaurant_domain.restaurant_domain_core.entity.Restaurant;
import org.example.restaurant_domain.restaurant_domain_core.enums.RestaurantStatus;
import org.example.restaurant_domain.restaurant_domain_core.gateway.RestaurantRepoGateway;
import org.example.restaurant_domain.restaurant_domain_core.valueobject.RestaurantId;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class RestaurantRepoGatewayImpl implements RestaurantRepoGateway {
    private final RestaurantRepository restaurantRepository;

    public RestaurantRepoGatewayImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        RestaurantEntity restaurantEntity = RestaurantMapper.toEntity(restaurant);
        restaurantEntity = restaurantRepository.save(restaurantEntity);
        return RestaurantMapper.toDomain(restaurantEntity);
    }

    @Override
    public Optional<Restaurant> findById(RestaurantId restaurantId) {
        return restaurantRepository.findById(restaurantId.getValue())
                .map(RestaurantMapper::toDomain);
    }

    @Override
    public Optional<Restaurant> findByOwnerId(UUID ownerId) {
        return restaurantRepository.findByOwnerId(ownerId)
                .map(RestaurantMapper::toDomain);
    }

    @Override
    public void deleteById(RestaurantId restaurantId) {
        restaurantRepository.deleteById(restaurantId.getValue());
    }

    @Override
    public boolean existsById(RestaurantId restaurantId) {
        return restaurantRepository.existsById(restaurantId.getValue());
    }

    @Override
    public Optional<Restaurant> findByIdAndStatus(RestaurantId restaurantId, RestaurantStatus status) {
        return restaurantRepository.findByIdAndStatus(restaurantId.getValue(), status)
                .map(RestaurantMapper::toDomain);
    }
}
