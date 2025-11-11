package org.example.payment_container.rest;

import org.example.payment_domain.payment_application_service.dto.PaymentResponseDto;
import org.example.payment_domain.payment_application_service.handler.PaymentStatusUpdatedUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController()
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentStatusUpdatedUseCase paymentStatusUpdatedUseCase;

    public PaymentController(PaymentStatusUpdatedUseCase paymentStatusUpdatedUseCase) {
        this.paymentStatusUpdatedUseCase = paymentStatusUpdatedUseCase;
    }

    @GetMapping("/simulate/{orderId}")
    public PaymentResponseDto simulatePaymentStatus(@PathVariable UUID orderId, 
                                                    @RequestParam("status") String status,
                                                    @RequestParam(value = "failureMessage", required = false) String failureMessage) {
        return paymentStatusUpdatedUseCase.execute(orderId, status,failureMessage);
    }
}
