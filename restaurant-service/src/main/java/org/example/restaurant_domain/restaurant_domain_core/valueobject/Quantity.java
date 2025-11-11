package org.example.restaurant_domain.restaurant_domain_core.valueobject;

import java.util.Objects;

import org.example.restaurant_domain.restaurant_domain_core.exception.MenuItemException;

public class Quantity {
    private final Integer value;

    public Quantity(Integer value) {
        if (value == null || value.intValue() < 0) {
            throw new IllegalArgumentException("Quantity value must be greater than or equal to 0");
        }
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        Quantity quantity = (Quantity) o;
        return Objects.equals(value, quantity.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    public void decreaseQuantity(int qty) {
        if (qty <= 0) {
            throw new MenuItemException("Quantity must be positive");
        }
        if (this.value < qty) {
            throw new MenuItemException("Not enough stock");
        }
    }
}
