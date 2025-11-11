package org.example.order_domain.order_domain_core.exception;

import org.example.order_domain.order_domain_core.enums.OrderStatus;

public class InvalidOrderStatusException extends RuntimeException {

    private final OrderStatus currentStatus;
    private final OrderStatus expectedStatus;

    public InvalidOrderStatusException(String message, OrderStatus currentStatus, OrderStatus expectedStatus) {
        super(message);
        this.currentStatus = currentStatus;
        this.expectedStatus = expectedStatus;
    }

    public InvalidOrderStatusException(String message, OrderStatus currentStatus) {
        super(message);
        this.currentStatus = currentStatus;
        this.expectedStatus = null;
    }

    public InvalidOrderStatusException(String message) {
        super(message);
        this.currentStatus = null;
        this.expectedStatus = null;
    }

    public OrderStatus getCurrentStatus() {
        return currentStatus;
    }

    public OrderStatus getExpectedStatus() {
        return expectedStatus;
    }

    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder(super.getMessage());
        if (currentStatus != null) {
            sb.append(" [Current status: ").append(currentStatus);
            if (expectedStatus != null) {
                sb.append(", Expected status: ").append(expectedStatus);
            }
            sb.append("]");
        }
        return sb.toString();
    }
}
