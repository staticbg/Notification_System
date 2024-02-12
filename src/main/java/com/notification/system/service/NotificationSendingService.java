package com.notification.system.service;


import com.notification.system.channel.Channel;
import com.notification.system.model.Message;
import com.notification.system.model.MessageStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class NotificationSendingService {
    private final Map<String, Channel> notificationChannels;
    private final PersistenceService persistenceService;

    @Autowired
    public NotificationSendingService(List<Channel> channels, PersistenceService persistenceService) {
        notificationChannels = new HashMap<>();
        for (Channel channel: channels) {
            notificationChannels.put(channel.getName(), channel);
        }
        this.persistenceService = persistenceService;
    }

    public void sendMessages(List<UUID> messageIds) {
        List<Message> messages = persistenceService.getMessagesByIds(messageIds);
        for (Message message: messages) {
            try {
                notificationChannels.get(message.getChannel()).sendNotification(message);
            } catch (Exception e) {
                persistenceService.updateMessageStatus(message, MessageStatus.FAILED);
            }
        }
    }

}
