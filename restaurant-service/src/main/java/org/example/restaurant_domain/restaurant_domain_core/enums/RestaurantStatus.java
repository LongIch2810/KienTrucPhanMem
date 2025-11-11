package org.example.restaurant_domain.restaurant_domain_core.enums;

public enum RestaurantStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    private final String value;

    RestaurantStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RestaurantStatus fromString(String value) {
        for (RestaurantStatus status : values()) {
            if (status.getValue().equalsIgnoreCase(value) || status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown restaurant status: " + value);
    }
}

