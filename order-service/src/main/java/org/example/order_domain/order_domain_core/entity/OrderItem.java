package org.example.order_domain.order_domain_core.entity;

import org.example.order_domain.order_domain_core.valueobject.Money;
import org.example.order_domain.order_domain_core.valueobject.OrderId;
import org.example.order_domain.order_domain_core.valueobject.ProductId;
import org.example.order_domain.order_domain_core.valueobject.Quantity;

public class OrderItem {
    private OrderId orderId;
    private final ProductId productId;
    private final Money price;
    private final Quantity quantity;
    private final Money subTotal;

    public OrderItem(ProductId productId, Quantity quantity, Money price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.subTotal = price.multiply(quantity.getValue());
    }

    public OrderItem(OrderId orderId, ProductId productId, Quantity quantity, Money price) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.subTotal = price.multiply(quantity.getValue());
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public void setOrderId(OrderId orderId) {
        this.orderId = orderId;
    }

    public ProductId getProductId() {
        return productId;
    }

    public Money getPrice() {
        return price;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public Money getSubTotal() {
        return subTotal;
    }
}
