package org.example.order_domain.order_domain_core.entity;

import org.example.order_domain.order_domain_core.enums.OrderStatus;
import org.example.order_domain.order_domain_core.enums.PaymentMethod;
import org.example.order_domain.order_domain_core.enums.PaymentStatus;
import org.example.order_domain.order_domain_core.event.DomainEvent;
import org.example.order_domain.order_domain_core.event.OrderCreatedEvent;
import org.example.order_domain.order_domain_core.valieobject.Money;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long id;
    private Long userId;
    private Money totalAmount;
    private Money discountAmount;
    private Money finalAmount;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    private PaymentMethod paymentMethod;
    private List<OrderItem>  orderItems = new ArrayList<>();
    private List<DomainEvent>  domainEvents = new ArrayList<>();
    public Order(Long id,Long userId,Money totalAmount,Money discountAmount,
                 Money finalAmount,OrderStatus orderStatus,
                 PaymentStatus paymentStatus,PaymentMethod paymentMethod,List<OrderItem>  orderItems) {
        this.id = id;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        this.finalAmount = finalAmount;
        this.orderStatus = orderStatus;
        this.paymentStatus = paymentStatus;
        this.paymentMethod = paymentMethod;
        this.orderItems = orderItems;
    }
    public Order(Long userId,Money totalAmount,Money discountAmount,
                 Money finalAmount,OrderStatus orderStatus,PaymentStatus paymentStatus,
                 PaymentMethod paymentMethod,List<OrderItem>  orderItems) {
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        this.finalAmount = finalAmount;
        this.orderStatus = orderStatus;
        this.paymentStatus = paymentStatus;
        this.paymentMethod = paymentMethod;
        this.orderItems = orderItems;
    }

    public Order(Long userId,Money totalAmount,Money discountAmount,
                 Money finalAmount,OrderStatus orderStatus,PaymentStatus paymentStatus,
                 PaymentMethod paymentMethod) {
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        this.finalAmount = finalAmount;
        this.orderStatus = orderStatus;
        this.paymentStatus = paymentStatus;
        this.paymentMethod = paymentMethod;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Money getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Money totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Money getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Money discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Money getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(Money finalAmount) {
        this.finalAmount = finalAmount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<DomainEvent> getDomainEvents() {
        return domainEvents;
    }

    public void maskCreated(){
        domainEvents.add(new OrderCreatedEvent(this.id));
    }

    public void maskPaid(){}

    public void maskCancel(){}
}
