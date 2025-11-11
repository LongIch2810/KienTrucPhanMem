package org.example.restaurant_domain.restaurant_application_service.port;

import org.example.restaurant_domain.restaurant_domain_core.event.PaymentCompletedEvent;

public interface ICheckAvailabilityAndApproveOrderUseCase {
    void execute(PaymentCompletedEvent event);
}
