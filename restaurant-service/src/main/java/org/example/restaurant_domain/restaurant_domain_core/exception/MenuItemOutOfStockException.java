package org.example.restaurant_domain.restaurant_domain_core.exception;

public class MenuItemOutOfStockException extends MenuItemException {
    public MenuItemOutOfStockException(String message) {
        super(message);
    }
}
