/**
 * Service responsible for the sending of notifications
 */

package com.notification.system.service;


import com.notification.system.channel.Channel;
import com.notification.system.model.Message;
import com.notification.system.model.MessageStatus;
import com.notification.system.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class NotificationSendingService {
    private final Map<String, Channel> notificationChannels;
    private final PersistenceService persistenceService;
    private final KafkaTemplate<String, UUID> kafkaTemplate;

    @Autowired
    public NotificationSendingService(
            List<Channel> channels,
            PersistenceService persistenceService,
            KafkaTemplate<String, UUID> kafkaTemplate
    ) {
        notificationChannels = new HashMap<>();
        for (Channel channel: channels) {
            notificationChannels.put(channel.getName(), channel);
        }
        this.persistenceService = persistenceService;
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Sends notification message by the message id
     */
    public void sendMessage(UUID messageId) {
        Optional<Message> messageResult = persistenceService.getMessageById(messageId);
        if (messageResult.isEmpty()) {
            return;
        }
        Message message = messageResult.get();
        try {
            notificationChannels.get(message.getChannel()).sendNotification(message);
            message.setStatus(MessageStatus.SENT);
            message.setSentTimestamp(new Timestamp(Calendar.getInstance().getTime().getTime()));
            persistenceService.save(message);
        } catch (Exception e) {
            message.setStatus(MessageStatus.FAILED);
            persistenceService.save(message);
        }
    }

    /**
     * Sends messages to the Kafka topic for all unprocessed Message entities
     */
    public void triggerNotificationSending() {
        List<Message> messages = persistenceService.getUnprocessedMessages();
        for (Message message: messages) {
            sendKafkaMessage(message.getId());
        }
    }

    /**
     * Sends message to the Kafka topic with the message id of an unprocessed Message entity
     */
    public void sendKafkaMessage(UUID messageId) {
        kafkaTemplate.send(Constants.TRIGGER_NOTIFICATION_SENDING_KAFKA_TOPIC, messageId);
    }

}
