package org.example.restaurant_domain.restaurant_domain_core.exception;

public class MenuItemException extends RuntimeException {

    public MenuItemException(String message) {
        super(message);
    }

    public MenuItemException(String message, Throwable cause) {
        super(message, cause);
    }
}