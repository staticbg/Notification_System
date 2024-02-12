/**
 * Email notification channel implementation
 */
package com.notification.system.channel;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.notification.system.model.Message;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Getter
@Slf4j
public class SlackChannel implements Channel {
    private final String name = "SLACK";

    /**
     * Method which sends notification via the Slack channel
     */
    @Override
    public void sendNotification(Message message) throws Exception {
        log.info("Sending notification for message {} via Slack channel", message.getId());
        Payload payload = Payload.builder()
            .text(message.getSubject() + "\n" + message.getContent())
            .build();
        try {
            Slack.getInstance().send(message.getRecipient(), payload);
            log.info("Successfully sent notification for message {} via Slack channel", message.getId());
        } catch (Exception e) {
            log.error("Slack channel error for message {}: {}", message.getId(), e.getMessage());
            throw new Exception("SLACK channel error: " + e.getMessage());
        }
    }

}
