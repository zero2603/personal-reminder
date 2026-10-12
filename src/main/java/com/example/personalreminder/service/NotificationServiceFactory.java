package com.example.personalreminder.service;

import org.springframework.stereotype.Component;

import java.util.Map;

/** Resolves Spring-managed notification services by their bean names (email or slack). */
@Component
public class NotificationServiceFactory {
    private final Map<String, NotificationService> services;

    public NotificationServiceFactory(Map<String, NotificationService> services) {
        this.services = services;
    }

    public NotificationService get(String type) {
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Notification type must not be blank");
        }
        NotificationService service = services.get(type);
        if (service == null) {
            throw new IllegalArgumentException("Unknown type: " + type);
        }
        return service;
    }
}
