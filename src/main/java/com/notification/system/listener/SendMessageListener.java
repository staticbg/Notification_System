package com.notification.system.listener;

import com.notification.system.service.NotificationSendingService;
import com.notification.system.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SendMessageListener {
    private final NotificationSendingService notificationSendingService;

    @KafkaListener(topics = Constants.TRIGGER_NOTIFICATION_SENDING_KAFKA_TOPIC, groupId = Constants.KAFKA_GROUP_ID)
    public void listenForMessageSending(UUID messageId) {
        notificationSendingService.sendMessage(messageId);
    }
}
