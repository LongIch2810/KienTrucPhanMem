package org.example.order_domain.order_domain_core.valieobject;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

public class Money {
    private final BigDecimal amount;
    private final Currency currency;

    public Money(BigDecimal amount, Currency currency) {
        if (amount == null || currency == null) {
            throw new IllegalArgumentException("Amount and currency must not be null");
        }
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.amount = amount;
        this.currency = currency;
    }

    private boolean isValid() {
        return amount != null &&
                currency != null &&
                amount.compareTo(BigDecimal.ZERO) >= 0;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Money add(Money other){
        if (amount == null || currency == null) {
            throw new IllegalArgumentException("Amount and currency must not be null");
        }
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        BigDecimal newAmount = amount.add(other.amount);
        return new Money(newAmount, this.currency);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(amount, money.amount) && Objects.equals(currency, money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }
}
