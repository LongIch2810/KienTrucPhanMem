package org.example.order_messaging.listener;

import org.example.order_domain.order_application_service.handler.UpdateOrderStatusUseCase;
import org.example.order_domain.order_domain_core.event.OrderCancelledEvent;
import org.example.order_domain.order_domain_core.event.PaymentCompletedEvent;
import org.example.order_domain.order_domain_core.event.PaymentFailedEvent;
import org.example.order_domain.order_domain_core.event.RestaurantApprovedEvent;
import org.example.order_domain.order_domain_core.event.RestaurantRejectedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageQueueListener {
    private static final Logger logger = LoggerFactory.getLogger(MessageQueueListener.class);
    private final UpdateOrderStatusUseCase updateOrderStatusUseCase;

    public MessageQueueListener(UpdateOrderStatusUseCase updateOrderStatusUseCase) {
        this.updateOrderStatusUseCase = updateOrderStatusUseCase;
    }

    @KafkaListener(topics = "${kafka.topics.payment-completed}", groupId = "${spring.kafka.consumer.group-id}")
    public void handleOrderCreatedEvent(PaymentCompletedEvent event) {
        logger.info("[ORDER-SERVICE] Nhận event: {}", event);
        this.updateOrderStatusUseCase.execute(event.getOrderId(), "PAID",null);
    }

    @KafkaListener(topics = "${kafka.topics.payment-failed}", groupId = "${spring.kafka.consumer.group-id}")
    public void handlePaymentFailedEvent(PaymentFailedEvent event) {
        logger.info("[ORDER-SERVICE] Nhận event: {}", event);
        this.updateOrderStatusUseCase.execute(event.getOrderId(), "CANCELLED",event.getFailureReason());
    }

    @KafkaListener(topics = "${kafka.topics.order-cancelled}", groupId = "${spring.kafka.consumer.group-id}")
    public void handleOrderCancelledEvent(OrderCancelledEvent event) {
        logger.info("[ORDER-SERVICE] Nhận event: {}", event);
        this.updateOrderStatusUseCase.execute(event.getOrderId(), "CANCELLED",event.getFailureMessage());
    }

    @KafkaListener(topics = "${kafka.topics.restaurant-rejected}", groupId = "${spring.kafka.consumer.group-id}")
    public void handleRestaurantRejectedEvent(RestaurantRejectedEvent event) {
        logger.info("[ORDER-SERVICE] Nhận event: {}", event);
        this.updateOrderStatusUseCase.execute(event.getOrderId(), "CANCELLING",event.getRejectionReason());
    }

    @KafkaListener(topics = "${kafka.topics.restaurant-approved}", groupId = "${spring.kafka.consumer.group-id}")
    public void handleRestaurantApprovedEvent(RestaurantApprovedEvent event) {
        logger.info("[ORDER-SERVICE] Nhận event: {}", event);
        this.updateOrderStatusUseCase.execute(event.getOrderId(), "APPROVED",null);
    }
}
