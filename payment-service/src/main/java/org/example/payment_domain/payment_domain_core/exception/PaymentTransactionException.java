package org.example.payment_domain.payment_domain_core.exception;

/**
 * Exception thrown when payment transaction operations fail
 */
public class PaymentTransactionException extends RuntimeException {
    public PaymentTransactionException(String message) {
        super(message);
    }

    public PaymentTransactionException(String message, Throwable cause) {
        super(message, cause);
    }
}

