package com.notification.system.service;


import com.notification.system.model.Message;
import com.notification.system.model.MessageStatus;
import com.notification.system.model.Notification;
import com.notification.system.model.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class NotificationService {

    private final PersistenceService persistenceService;

    public void sendNotification(NotificationRequest notificationRequest) {
        // Validation that notificationChannel exists is done in the NotificationController
        // thus here should be guaranteed that the notificationChannel exists
        List<Message> messages = new ArrayList<>();
        for (String recipient: notificationRequest.getRecipients()) {
            Message message = new Message();
            message.setSubject(notificationRequest.getSubject());
            message.setContent(notificationRequest.getContent());
            message.setRecipient(recipient);
            message.setStatus(MessageStatus.NEW);
            message.setChannel(notificationRequest.getChannel());
            messages.add(message);
        }
        Notification notification = new Notification();
        notification.setMessages(messages);
        persistenceService.save(notification);
    }

    public List<Message> getMessagesForNotification(UUID notificationId) {
        return persistenceService.getMessagesForNotification(notificationId);
    }

    public List<Message> getUnprocessedMessages() {
        return persistenceService.getUnprocessedMessages();
    }

}
