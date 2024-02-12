package com.notification.system.service;


import com.notification.system.model.Message;
import com.notification.system.model.MessageStatus;
import com.notification.system.model.Notification;
import com.notification.system.persistence.MessageRepository;
import com.notification.system.persistence.NotificationRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Persistent
@RequiredArgsConstructor
public class PersistenceService {

    private final NotificationRepository notificationRepository;
    private final MessageRepository messageRepository;

    @Transactional
    public void save(Notification notification) {
        notificationRepository.save(notification);
    }

    public Notification getNotification(UUID id) {
        return notificationRepository.getReferenceById(id);
    }

    public List<Message> getUnprocessedMessages() {
        Specification<Message> specification = (root, query, cb) ->
                cb.notEqual(root.<MessageStatus>get("status"), MessageStatus.SENT);
        return messageRepository.findAll(specification);
    }

    public List<Message> getMessagesForNotification(UUID notificationId) {
        Specification<Message> specification = (root, query, cb) ->
                cb.equal(root.<UUID>get("notification"), notificationId);
        return messageRepository.findAll(specification);
    }

    public List<Message> getMessagesByIds(List<UUID> messageIds) {
        Specification<Message> specification = (root, query, builder) -> {
            final Path<UUID> group = root.get("id");
            return group.in(messageIds);
        };

        return messageRepository.findAll(specification);
    }

    @Transactional
    public void updateMessageStatus(Message message, MessageStatus messageStatus) {
        message.setStatus(messageStatus);
        messageRepository.save(message);
    }

}
