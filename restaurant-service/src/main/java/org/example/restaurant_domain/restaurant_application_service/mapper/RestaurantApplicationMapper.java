package org.example.restaurant_domain.restaurant_application_service.mapper;

import org.example.restaurant_domain.restaurant_application_service.dto.RestaurantOrderResponseDto;
import org.example.restaurant_domain.restaurant_domain_core.entity.RestaurantOrder;

public class RestaurantApplicationMapper {
    static public RestaurantOrderResponseDto toResponseDto(RestaurantOrder restaurantOrder) {
        return new RestaurantOrderResponseDto();
    }

    static public RestaurantOrder toRestaurantOrder(){
        return new RestaurantOrder();
    }
}
