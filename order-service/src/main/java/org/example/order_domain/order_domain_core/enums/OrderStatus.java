package org.example.order_domain.order_domain_core.enums;

public enum OrderStatus {
    PENDING("PENDING"),
    PAID("PAID"),
    APPROVED("APPROVED"),
    CANCELLING("CANCELLING"),
    CANCELLED("CANCELLED");
    private final String value;
    private OrderStatus(String value) {
      this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static OrderStatus fromString(String value) {
        for (OrderStatus status : values()) {
            if (status.getValue().equalsIgnoreCase(value) || status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown payment method: " + value);
    }
}
