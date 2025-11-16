package org.example.restaurant_domain.restaurant_application_service.handler;

import java.util.List;
import java.util.Optional;

import org.example.restaurant_domain.restaurant_application_service.port.ICheckAvailabilityAndApproveOrderUseCase;
import org.example.restaurant_domain.restaurant_domain_core.entity.MenuItem;
import org.example.restaurant_domain.restaurant_domain_core.entity.OrderItem;
import org.example.restaurant_domain.restaurant_domain_core.entity.Restaurant;
import org.example.restaurant_domain.restaurant_domain_core.entity.RestaurantOrder;
import org.example.restaurant_domain.restaurant_domain_core.enums.OrderApprovalStatus;
import org.example.restaurant_domain.restaurant_domain_core.enums.RestaurantStatus;
import org.example.restaurant_domain.restaurant_domain_core.event.PaymentCompletedEvent;
import org.example.restaurant_domain.restaurant_domain_core.event.RestaurantApprovedEvent;
import org.example.restaurant_domain.restaurant_domain_core.event.RestaurantRejectedEvent;
import org.example.restaurant_domain.restaurant_domain_core.gateway.DomainEventPublisher;
import org.example.restaurant_domain.restaurant_domain_core.gateway.MenuItemRepoGateway;
import org.example.restaurant_domain.restaurant_domain_core.gateway.RestaurantOrderRepoGateway;
import org.example.restaurant_domain.restaurant_domain_core.gateway.RestaurantRepoGateway;
import org.example.restaurant_domain.restaurant_domain_core.valueobject.MenuItemId;
import org.example.restaurant_domain.restaurant_domain_core.valueobject.Money;
import org.example.restaurant_domain.restaurant_domain_core.valueobject.OrderId;
import org.example.restaurant_domain.restaurant_domain_core.valueobject.Quantity;
import org.example.restaurant_domain.restaurant_domain_core.valueobject.RestaurantId;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class CheckAvailabilityAndApproveOrderUseCase implements ICheckAvailabilityAndApproveOrderUseCase {
    private final RestaurantRepoGateway restaurantRepoGateway;
    private final RestaurantOrderRepoGateway restaurantOrderRepoGateway;
    private final MenuItemRepoGateway menuItemRepoGateway;
    private final DomainEventPublisher domainEventPublisher;

    public CheckAvailabilityAndApproveOrderUseCase(RestaurantRepoGateway restaurantRepoGateway,
            RestaurantOrderRepoGateway restaurantOrderRepoGateway,
            MenuItemRepoGateway menuItemRepoGateway,
            DomainEventPublisher domainEventPublisher) {
        this.restaurantRepoGateway = restaurantRepoGateway;
        this.restaurantOrderRepoGateway = restaurantOrderRepoGateway;
        this.menuItemRepoGateway = menuItemRepoGateway;
        this.domainEventPublisher = domainEventPublisher;
    }

    @Override
    public void execute(PaymentCompletedEvent event) {
        OrderId orderId = new OrderId(event.getOrderId());
        Optional<RestaurantOrder> existingOrderOpt = restaurantOrderRepoGateway.findByOrderId(orderId);

        if (existingOrderOpt.isPresent()) {
            rejectOrder(existingOrderOpt.get(), "The order has been processed by the restaurant");
            return;
        }

        Optional<Restaurant> restaurantExists = restaurantRepoGateway
                .findByIdAndStatus(new RestaurantId(event.getRestaurantId()), RestaurantStatus.ACTIVE);
        RestaurantOrder newRestaurantOrder = new RestaurantOrder(new OrderId(event.getOrderId()),
                new RestaurantId(event.getRestaurantId()), new Money(event.getAmount()),
                OrderApprovalStatus.PENDING);
        newRestaurantOrder = this.restaurantOrderRepoGateway.save(newRestaurantOrder);
        if (restaurantExists.isEmpty()) {
            rejectOrder(newRestaurantOrder, "Restaurant is not active");
            return;
        }

        boolean allItemsAvailable = event.getItems().stream()
                .allMatch(this::isOrderItemAvailable);

        if (!allItemsAvailable) {
            rejectOrder(newRestaurantOrder, "One or more menu items are not available");
            return;
        }

        if (!reserveStock(event.getItems())) {
            rejectOrder(newRestaurantOrder, "Failed to reserve stock for one or more menu items");
            return;
        }

        approveOrder(newRestaurantOrder);
    }

    private void rejectOrder(RestaurantOrder restaurantOrder, String reason) {
        restaurantOrder.markRejected(reason);
        this.restaurantOrderRepoGateway.update(restaurantOrder);
        RestaurantRejectedEvent rejectedEvent = new RestaurantRejectedEvent(
                restaurantOrder.getId().getValue(),
                restaurantOrder.getOrderId().getValue(),
                restaurantOrder.getRestaurantId().getValue(),
                restaurantOrder.getTotalAmount().getAmount(),
                restaurantOrder.getApprovalStatus().name(),
                restaurantOrder.getRejectionReason(),
                restaurantOrder.getCreatedAt(),
                restaurantOrder.getUpdatedAt());
        domainEventPublisher.publish(rejectedEvent);
    }

    private void approveOrder(RestaurantOrder restaurantOrder) {
        restaurantOrder.markApproved();
        this.restaurantOrderRepoGateway.update(restaurantOrder);
        RestaurantApprovedEvent approvedEvent = new RestaurantApprovedEvent(
                restaurantOrder.getId().getValue(),
                restaurantOrder.getOrderId().getValue(),
                restaurantOrder.getRestaurantId().getValue(),
                restaurantOrder.getTotalAmount().getAmount(),
                restaurantOrder.getApprovalStatus().getValue(),
                restaurantOrder.getCreatedAt(),
                restaurantOrder.getUpdatedAt());
        domainEventPublisher.publish(approvedEvent);
    }

    private boolean reserveStock(List<OrderItem> items) {
        for (OrderItem orderItem : items) {
            Optional<MenuItem> menuItemOpt = menuItemRepoGateway.findById(new MenuItemId(orderItem.getProductId()));

            if (menuItemOpt.isEmpty()) {
                return false;
            }

            MenuItem menuItem = menuItemOpt.get();
            int currentQuantity = menuItem.getQuantity().getValue();
            int orderedQuantity = orderItem.getQuantity();

            menuItem.setQuantity(new Quantity(currentQuantity - orderedQuantity));
            menuItem.setAvailable(menuItem.getQuantity().getValue() != 0);
            menuItemRepoGateway.save(menuItem);
        }

        return true;

    }

    private boolean isOrderItemAvailable(OrderItem orderItem) {
        Optional<MenuItem> menuItemOpt = menuItemRepoGateway.findById(new MenuItemId(orderItem.getProductId()));

        if (menuItemOpt.isEmpty()) {
            return false;
        }

        MenuItem menuItem = menuItemOpt.get();
        return menuItem.isAvailable()
                && menuItem.getQuantity().getValue() >= orderItem.getQuantity();
    }
}
