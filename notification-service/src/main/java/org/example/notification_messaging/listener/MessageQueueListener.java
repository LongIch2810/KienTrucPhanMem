package org.example.notification_messaging.listener;

import org.example.notification_domain.notification_domain_core.event.OrderCancelledEvent;
import org.example.notification_domain.notification_domain_core.event.OrderCreatedEvent;
import org.example.notification_domain.notification_domain_core.event.PaymentCompletedEvent;
import org.example.notification_domain.notification_domain_core.event.PaymentFailedEvent;
import org.example.notification_domain.notification_domain_core.event.RestaurantApprovedEvent;
import org.example.notification_domain.notification_domain_core.event.RestaurantRejectedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageQueueListener {
    private static final Logger log = LoggerFactory.getLogger(MessageQueueListener.class);
    
    @KafkaListener(topics = "${kafka.topics.order-created}", groupId = "${spring.kafka.consumer.group-id}")
    public void handleOrderCreated(OrderCreatedEvent event) {
        log.info("=================================================================");
        log.info("[NOTIFICATION-SERVICE] EVENT RECEIVED: OrderCreatedEvent");
        log.info("FROM: Order Service | TOPIC: order.created");
        log.info("-----------------------------------------------------------------");
        log.info("Order ID: {}", event.getOrderId());
        log.info("Customer ID: {}", event.getCustomerId());
        log.info("Restaurant ID: {}", event.getRestaurantId());
        log.info("Tracking ID: {}", event.getTrackingId());
        log.info("Total Amount: {}", event.getPrice());
        log.info("Status: {}", event.getOrderStatus());
        log.info("Items Count: {}", event.getItems() != null ? event.getItems().size() : 0);
        log.info("Occurred On: {}", event.occurredOn());
    }

    @KafkaListener(topics = "${kafka.topics.payment-completed}", groupId = "${spring.kafka.consumer.group-id}")
    public void handlePaymentCompleted(PaymentCompletedEvent event) {
        log.info("=================================================================");
        log.info("[NOTIFICATION-SERVICE] EVENT RECEIVED: PaymentCompletedEvent");
        log.info("FROM: Payment Service | TOPIC: payment.completed");
        log.info("-----------------------------------------------------------------");
        log.info("Payment ID: {}", event.getPaymentId());
        log.info("Order ID: {}", event.getOrderId());
        log.info("Amount: {} VND", event.getAmount());
        log.info("Transaction ID: {}", event.getTransactionId());
        log.info("Completed At: {}", event.getCompletedAt());
        log.info("Occurred On: {}", event.occurredOn());
    }

    @KafkaListener(topics = "${kafka.topics.payment-failed}", groupId = "${spring.kafka.consumer.group-id}")
    public void handlePaymentFailed(PaymentFailedEvent event) {
        log.info("=================================================================");
        log.info("[NOTIFICATION-SERVICE] EVENT RECEIVED: PaymentFailedEvent");
        log.info("FROM: Payment Service | TOPIC: payment.failed");
        log.info("-----------------------------------------------------------------");
        log.info("Payment ID: {}", event.getPaymentId());
        log.info("Order ID: {}", event.getOrderId());
        log.info("Amount: {} VND", event.getAmount());
        log.info("Failure Reason: {}", event.getFailureReason());
        log.info("Failed At: {}", event.getFailedAt());
        log.info("Occurred On: {}", event.occurredOn());
    }

    @KafkaListener(topics = "${kafka.topics.order-cancelled}", groupId = "${spring.kafka.consumer.group-id}")
    public void handleOrderCancelled(OrderCancelledEvent event) {
        log.info("=================================================================");
        log.info("[NOTIFICATION-SERVICE] EVENT RECEIVED: OrderCancelledEvent");
        log.info("FROM: Order Service | TOPIC: order.cancelled");
        log.info("-----------------------------------------------------------------");
        log.info("Order ID: {}", event.getOrderId());
        log.info("Failure Message: {}", event.getFailureMessage());
        log.info("Occurred On: {}", event.occurredOn());
    }

    @KafkaListener(topics = "${kafka.topics.restaurant-approved}", groupId = "${spring.kafka.consumer.group-id}")
    public void handleRestaurantApproved(RestaurantApprovedEvent event) {
        log.info("=================================================================");
        log.info("[NOTIFICATION-SERVICE] EVENT RECEIVED: RestaurantApprovedEvent");
        log.info("FROM: Restaurant Service | TOPIC: restaurant.approved");
        log.info("-----------------------------------------------------------------");
        log.info("Restaurant Order ID: {}", event.getRestaurantOrderId());
        log.info("Order ID: {}", event.getOrderId());
        log.info("Restaurant ID: {}", event.getRestaurantId());
        log.info("Total Amount: {} VND", event.getTotalAmount());
        log.info("Approval Status: {}", event.getApprovalStatus());
        log.info("Created At: {}", event.getCreatedAt());
        log.info("Updated At: {}", event.getUpdatedAt());
        log.info("Occurred On: {}", event.occurredOn());
    }

    @KafkaListener(topics = "${kafka.topics.restaurant-rejected}", groupId = "${spring.kafka.consumer.group-id}")
    public void handleRestaurantRejected(RestaurantRejectedEvent event) {
        log.info("=================================================================");
        log.info("[NOTIFICATION-SERVICE] EVENT RECEIVED: RestaurantRejectedEvent");
        log.info("FROM: Restaurant Service | TOPIC: restaurant.rejected");
        log.info("-----------------------------------------------------------------");
        log.info("Restaurant Order ID: {}", event.getRestaurantOrderId());
        log.info("Order ID: {}", event.getOrderId());
        log.info("Restaurant ID: {}", event.getRestaurantId());
        log.info("Total Amount: {} VND", event.getTotalAmount());
        log.info("Approval Status: {}", event.getApprovalStatus());
        log.info("Rejection Reason: {}", event.getRejectionReason());
        log.info("Created At: {}", event.getCreatedAt());
        log.info("Updated At: {}", event.getUpdatedAt());
        log.info("Occurred On: {}", event.occurredOn());
    }
}
