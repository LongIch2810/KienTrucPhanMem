package org.example.order_domain.order_domain_core.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentMethod {
   COD("COD"),
   ONLINE("ONLINE");
   private final String value;
   private PaymentMethod(String value) {
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
