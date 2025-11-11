package org.example.restaurant_dataaccess.adapter;

import org.example.restaurant_dataaccess.entity.MenuItemEntity;
import org.example.restaurant_dataaccess.mapper.MenuItemMapper;
import org.example.restaurant_dataaccess.repository.MenuItemRepository;
import org.example.restaurant_domain.restaurant_domain_core.entity.MenuItem;
import org.example.restaurant_domain.restaurant_domain_core.gateway.MenuItemRepoGateway;
import org.example.restaurant_domain.restaurant_domain_core.valueobject.MenuItemId;
import org.example.restaurant_domain.restaurant_domain_core.valueobject.RestaurantId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MenuItemRepoGatewayImpl implements MenuItemRepoGateway {
    private final MenuItemRepository menuItemRepository;

    public MenuItemRepoGatewayImpl(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
       
    }

    @Override
    public MenuItem save(MenuItem menuItem) {
        MenuItemEntity menuItemEntity = MenuItemMapper.toEntity(menuItem);
        menuItemEntity = menuItemRepository.save(menuItemEntity);
        return MenuItemMapper.toDomain(menuItemEntity);
    }

    @Override
    public Optional<MenuItem> findById(MenuItemId menuItemId) {
        return menuItemRepository.findById(menuItemId.getValue())
                .map(MenuItemMapper::toDomain);
    }

    @Override
    public List<MenuItem> findAllByRestaurantId(RestaurantId restaurantId) {
        return menuItemRepository.findAllByRestaurantId(restaurantId.getValue()).stream()
                .map(MenuItemMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(MenuItemId menuItemId) {
        menuItemRepository.deleteById(menuItemId.getValue());
    }

    @Override
    public boolean existsById(MenuItemId menuItemId) {
        return menuItemRepository.existsById(menuItemId.getValue());
    }
}
