package com.tinah.notification.config;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaNotificationListener {
    @KafkaListener(topics = "payment-events", groupId = "notification-service")
    public void onPaymentEvent(String event) {
        // Replace with email/SMS/push provider integration in production.
        System.out.println("Notification event received: " + event);
    }
}
