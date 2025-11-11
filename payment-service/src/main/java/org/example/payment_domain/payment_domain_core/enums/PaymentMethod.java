package org.example.payment_domain.payment_domain_core.enums;

public enum PaymentMethod {
    ONLINE("ONLINE"),
    COD("COD");

    private final String value;

    PaymentMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PaymentMethod fromString(String value) {
        for (PaymentMethod method : values()) {
            if (method.getValue().equalsIgnoreCase(value) || method.name().equalsIgnoreCase(value)) {
                return method;
            }
        }
        throw new IllegalArgumentException("Unknown payment method: " + value);
    }
}

