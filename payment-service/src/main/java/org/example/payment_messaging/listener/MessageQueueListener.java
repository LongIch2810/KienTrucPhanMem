package org.example.payment_messaging.listener;

import org.example.payment_domain.payment_application_service.dto.PaymentCreatedDto;
import org.example.payment_domain.payment_application_service.dto.PaymentResponseDto;
import org.example.payment_domain.payment_application_service.handler.PaymentCreatedUseCase;
import org.example.payment_domain.payment_application_service.handler.PaymentStatusUpdatedUseCase;
import org.example.payment_domain.payment_domain_core.entity.OrderItem;
import org.example.payment_domain.payment_domain_core.enums.PaymentMethod;
import org.example.payment_domain.payment_domain_core.event.OrderCreatedEvent;
import org.example.payment_domain.payment_domain_core.event.RestaurantRejectedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MessageQueueListener {
    private static final Logger logger = LoggerFactory.getLogger(MessageQueueListener.class);
    private final PaymentCreatedUseCase paymentCreatedUseCase;
    private final PaymentStatusUpdatedUseCase paymentStatusUpdatedUseCase;

    public MessageQueueListener(PaymentCreatedUseCase paymentCreatedUseCase,
            PaymentStatusUpdatedUseCase paymentStatusUpdatedUseCase) {
        this.paymentCreatedUseCase = paymentCreatedUseCase;
        this.paymentStatusUpdatedUseCase = paymentStatusUpdatedUseCase;
    }

    @KafkaListener(topics = "${kafka.topics.order-created}", groupId = "${spring.kafka.consumer.group-id}")
    public void handleOrderCreatedEvent(OrderCreatedEvent event) {
        logger.info("[PAYMENT-SERVICE] Nhận event: {}", event);

        PaymentCreatedDto paymentCreatedDto = new PaymentCreatedDto();
        paymentCreatedDto.setOrderId(event.getOrderId());
        paymentCreatedDto.setCustomerId(event.getCustomerId());
        paymentCreatedDto.setRestaurantId(event.getRestaurantId());
        paymentCreatedDto.setAmount(event.getPrice());
        paymentCreatedDto.setPaymentMethod(PaymentMethod.ONLINE.getValue());
        paymentCreatedDto.setOrderItems(
                event.getItems().stream()
                        .map(item -> new OrderItem(
                                item.getProductId(),
                                item.getQuantity(),
                                item.getPrice(),
                                item.getPrice().multiply(new BigDecimal(item.getQuantity()))))
                        .toList());

        PaymentResponseDto response = paymentCreatedUseCase.execute(paymentCreatedDto);

        logger.info("[PAYMENT-SERVICE] Tạo payment thành công với ID: {}", response.getId());
        logger.info("[PAYMENT-SERVICE] Chi tiết đơn hàng:");
        logger.info("Restaurant ID: {}", response.getRestaurantId());
        logger.info("Số lượng món: {}", response.getOrderItems().size());
        logger.info("Tổng tiền: {}", response.getAmount());
        logger.info("[PAYMENT-SERVICE] ĐANG CHỜ THANH TOÁN ....");
    }

    @KafkaListener(topics = "${kafka.topics.restaurant-rejected}", groupId = "${spring.kafka.consumer.group-id}")
    public void handleRestaurantRejectedEvent(RestaurantRejectedEvent event) {
        logger.info("[PAYMENT-SERVICE] Nhận event: {}", event);
        logger.info("[PAYMENT-SERVICE] ĐANG Xử Lý HOÀN TIỀN ....");
        this.paymentStatusUpdatedUseCase.execute(event.getOrderId(), "REFUNDED", event.getRejectionReason());
    }
}
