package com.kshrd.kroya_api.service.Notification;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PushNotificationService {
    private final Logger logger = LoggerFactory.getLogger(PushNotificationService.class);

    public void sendNotification(String token, String title, String messageBody) {
        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(messageBody)
                .build();

        Message message = Message.builder()
                .setToken(token)
                .setNotification(notification)
                .build();

        try {
            String response = FirebaseMessaging.getInstance().sendAsync(message).get();
            logger.info("Notification sent successfully. Response: " + response);
        } catch (Exception e) {
            logger.error("Failed to send FCM notification: ", e);
        }
    }
}
