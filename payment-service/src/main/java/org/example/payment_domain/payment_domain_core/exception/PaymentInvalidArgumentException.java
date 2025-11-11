package org.example.payment_domain.payment_domain_core.exception;

/**
 * Exception thrown when payment arguments are invalid
 */
public class PaymentInvalidArgumentException extends RuntimeException {
    public PaymentInvalidArgumentException(String message) {
        super(message);
    }

    public PaymentInvalidArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
}

