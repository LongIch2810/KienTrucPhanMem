package org.example.restaurant_domain.restaurant_domain_core.entity;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.example.restaurant_domain.restaurant_domain_core.enums.RestaurantStatus;

public class Restaurant {
    private UUID id;
    private String name;
    private UUID ownerId;
    private RestaurantStatus status;
    private OffsetDateTime createdAt;

    public Restaurant() {}

    public Restaurant(UUID id, String name, UUID ownerId, RestaurantStatus status, OffsetDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.status = status;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public RestaurantStatus getStatus() {
        return status;
    }

    public void setStatus(RestaurantStatus status) {
        this.status = status;
    }


    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
