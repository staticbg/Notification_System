/**
 * Listener for Kafka messages which trigger notification sending
 */

package com.notification.system.listener;

import com.notification.system.service.NotificationSendingService;
import com.notification.system.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class SendMessageListener {
    private final NotificationSendingService notificationSendingService;

    /**
     * Listener which triggers notification sending logic for the specified message id
     */
    @KafkaListener(topics = Constants.TRIGGER_NOTIFICATION_SENDING_KAFKA_TOPIC, groupId = Constants.KAFKA_GROUP_ID)
    public void listenForMessageSending(UUID messageId) {
        log.info("Received message {} via Kafka topic", messageId);
        notificationSendingService.sendMessage(messageId);
    }
}
