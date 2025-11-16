package org.example.restaurant_dataaccess.adapter;

import org.example.restaurant_dataaccess.entity.RestaurantOrderEntity;
import org.example.restaurant_dataaccess.mapper.RestaurantOrderMapper;
import org.example.restaurant_dataaccess.repository.RestaurantOrderRepository;
import org.example.restaurant_domain.restaurant_domain_core.entity.RestaurantOrder;
import org.example.restaurant_domain.restaurant_domain_core.gateway.RestaurantOrderRepoGateway;
import org.example.restaurant_domain.restaurant_domain_core.valueobject.OrderId;
import org.example.restaurant_domain.restaurant_domain_core.valueobject.RestaurantId;
import org.example.restaurant_domain.restaurant_domain_core.valueobject.RestaurantOrderId;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RestaurantOrderRepoGatewayImpl implements RestaurantOrderRepoGateway {
    private final RestaurantOrderRepository restaurantOrderRepository;

    public RestaurantOrderRepoGatewayImpl(RestaurantOrderRepository restaurantOrderRepository) {
        this.restaurantOrderRepository = restaurantOrderRepository;
    }

    @Override
    public RestaurantOrder save(RestaurantOrder restaurantOrder) {
        RestaurantOrderEntity restaurantOrderEntity = RestaurantOrderMapper.toEntity(restaurantOrder);
        restaurantOrderEntity = restaurantOrderRepository.save(restaurantOrderEntity);
        return RestaurantOrderMapper.toDomain(restaurantOrderEntity);
    }

    @Override
    public Optional<RestaurantOrder> findById(RestaurantOrderId restaurantOrderId) {
        return restaurantOrderRepository.findById(restaurantOrderId.getValue())
                .map(RestaurantOrderMapper::toDomain);
    }

    @Override
    public Optional<RestaurantOrder> findByOrderId(OrderId orderId) {
        return restaurantOrderRepository.findByOrderId(orderId.getValue())
                .map(RestaurantOrderMapper::toDomain);
    }

    @Override
    public List<RestaurantOrder> findAllByRestaurantId(RestaurantId restaurantId) {
        return restaurantOrderRepository.findAllByRestaurantId(restaurantId.getValue()).stream()
                .map(RestaurantOrderMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(RestaurantOrderId restaurantOrderId) {
        return restaurantOrderRepository.existsById(restaurantOrderId.getValue());
    }

    @Override
    public void update(RestaurantOrder restaurantOrder) {
        RestaurantOrderEntity restaurantOrderEntity = RestaurantOrderMapper.toEntity(restaurantOrder);
        restaurantOrderEntity.setId(restaurantOrder.getId().getValue());
        restaurantOrderEntity.setUpdatedAt(OffsetDateTime.now());
        restaurantOrderRepository.save(restaurantOrderEntity);
    }
}
