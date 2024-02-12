/**
 * Service responsible for the Message and Notification entities persistence
 */

package com.notification.system.service;


import com.notification.system.model.Message;
import com.notification.system.model.MessageStatus;
import com.notification.system.model.Notification;
import com.notification.system.persistence.MessageRepository;
import com.notification.system.persistence.NotificationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Persistent
@RequiredArgsConstructor
@Transactional
public class PersistenceService {

    private final NotificationRepository notificationRepository;
    private final MessageRepository messageRepository;

    /**
     * Saves Notification entity
     */
    public void save(Notification notification) {
        notificationRepository.save(notification);
    }

    /**
     * Saves Message entity
     */
    public void save(Message message) {
        messageRepository.save(message);
    }

    /**
     * Returns all unprocessed Message entities (with status != SENT)
     */
    public List<Message> getUnprocessedMessages() {
        Specification<Message> specification = (root, query, cb) ->
                cb.notEqual(root.<MessageStatus>get("status"), MessageStatus.SENT);
        return messageRepository.findAll(specification);
    }

    /**
     * Returns all messages for a specific notification by notification id
     */
    public List<Message> getMessagesForNotification(UUID notificationId) {
        Specification<Message> specification = (root, query, cb) ->
                cb.equal(root.<UUID>get("notification"), notificationId);
        return messageRepository.findAll(specification);
    }

    /**
     * Returns message by message id
     */
    public Optional<Message> getMessageById(UUID messageId) {
        return messageRepository.findById(messageId);
    }

}
