package com.notification.system.channel;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.notification.system.model.Notification;
import lombok.Getter;
import org.springframework.stereotype.Service;


@Service
@Getter
public class SlackChannel implements Channel {
    private final String name = "SLACK";

    @Override
    public void sendNotification(Notification notification) throws Exception {
        Payload payload = Payload.builder()
            .text(notification.getSubject() + "\n" + notification.getContent())
            .build();
        try {
            for (String slackWebhookUrl: notification.getRecipients()) {
                Slack.getInstance().send(slackWebhookUrl, payload);
            }
        } catch (Exception e) {
            throw new Exception("SLACK channel error: " + e.getMessage());
        }
    }

}
