package org.example.payment_domain.payment_domain_core.exception;

/**
 * Exception thrown when payment state transition is invalid
 */
public class PaymentInvalidStateException extends RuntimeException {
    public PaymentInvalidStateException(String message) {
        super(message);
    }

    public PaymentInvalidStateException(String message, Throwable cause) {
        super(message, cause);
    }
}

