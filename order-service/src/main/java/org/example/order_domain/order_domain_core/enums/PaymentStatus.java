package org.example.order_domain.order_domain_core.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentStatus {

    PENDING("PENDING"),
    PAID("PAID"),
    FAILED("FAILED"),
    REFUNDED("REFUNDED");
    private final String value;
    private PaymentStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


    public static PaymentStatus fromString(String value) {
        for (PaymentStatus status : values()) {
            if (status.getValue().equalsIgnoreCase(value) || status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown payment method: " + value);
    }
}
