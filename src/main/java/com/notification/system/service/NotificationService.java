package com.notification.system.service;


import com.notification.system.channel.Channel;
import com.notification.system.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotificationService {
    private final Map<String, Channel> notificationChannels;

    @Autowired
    public NotificationService(List<Channel> channels) {
        notificationChannels = new HashMap<>();
        for (Channel channel: channels) {
            notificationChannels.put(channel.getName(), channel);
        }
    }

    public void sendNotification(Notification notification) {
        for (String notificationChannel: notification.getChannels()) {
            // Validation that notificationChannel exists is done in the NotificationController
            // thus here should be guaranteed that the notificationChannel exists
            notificationChannels
                    .get(notificationChannel.toUpperCase())
                    .sendNotification(
                        notification.getContent(),
                        notification.getRecipients()
                    );
        }
    }
}
