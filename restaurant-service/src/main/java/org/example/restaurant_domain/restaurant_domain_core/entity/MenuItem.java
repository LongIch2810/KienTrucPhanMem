package org.example.restaurant_domain.restaurant_domain_core.entity;

import org.example.restaurant_domain.restaurant_domain_core.valueobject.MenuItemId;
import org.example.restaurant_domain.restaurant_domain_core.valueobject.Money;
import org.example.restaurant_domain.restaurant_domain_core.valueobject.Quantity;
import org.example.restaurant_domain.restaurant_domain_core.valueobject.RestaurantId;

public class MenuItem {
    private MenuItemId id;
    private RestaurantId restaurantId;
    private String name;
    private Money price;
    private Quantity quantity;
    private boolean available;

    public MenuItem(MenuItemId id, RestaurantId restaurantId, String name, Money price, Quantity quantity, boolean available) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.available = available;
    }

    public MenuItemId getId() {
        return id;
    }

    public void setId(MenuItemId id) {
        this.id = id;
    }

    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(RestaurantId restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
