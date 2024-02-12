/**
 * Service responsible for Notification operations
 */

package com.notification.system.service;


import com.notification.system.model.Message;
import com.notification.system.model.MessageStatus;
import com.notification.system.model.Notification;
import com.notification.system.model.NotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final PersistenceService persistenceService;

    /**
     * Creates Notification entity and saves it to DB for future processing
     */
    public void createNotification(NotificationRequest notificationRequest) {
        // Validation that notificationChannel exists is done in the NotificationController
        // thus here should be guaranteed that the notificationChannel exists
        log.info("Creating new notification and messages");
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
        notification.setCreatedTimestamp(new Timestamp(Calendar.getInstance().getTime().getTime()));
        notification.setMessages(messages);
        persistenceService.save(notification);
    }

    /**
     * Returns all messages for a notification by notification id
     */
    public List<Message> getMessagesForNotification(UUID notificationId) {
        return persistenceService.getMessagesForNotification(notificationId);
    }

    /**
     * Returns all unprocessed messages (status != SENT)
     */
    public List<Message> getUnprocessedMessages() {
        return persistenceService.getUnprocessedMessages();
    }

}
