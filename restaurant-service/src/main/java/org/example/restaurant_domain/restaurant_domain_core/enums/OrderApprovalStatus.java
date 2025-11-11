package org.example.restaurant_domain.restaurant_domain_core.enums;

public enum OrderApprovalStatus {
    PENDING("PENDING"),
    APPROVED("APPROVED"),
    REJECTED("REJECTED");

    private final String value;

    OrderApprovalStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static OrderApprovalStatus fromString(String value) {
        for (OrderApprovalStatus status : values()) {
            if (status.getValue().equalsIgnoreCase(value) || status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown order approval status: " + value);
    }
}

