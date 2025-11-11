package org.example.restaurant_domain.restaurant_domain_core.valueobject;

import java.util.Objects;
import java.util.UUID;

public class RestaurantOrderId {
    private final UUID value;

    public RestaurantOrderId(UUID value) {
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RestaurantOrderId)) return false;
        RestaurantOrderId that = (RestaurantOrderId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}