package com.notification.system.channel;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.notification.system.model.Message;
import lombok.Getter;
import org.springframework.stereotype.Service;


@Service
@Getter
public class SlackChannel implements Channel {
    private final String name = "SLACK";

    @Override
    public void sendNotification(Message message) throws Exception {
        Payload payload = Payload.builder()
            .text(message.getSubject() + "\n" + message.getContent())
            .build();
        try {
            Slack.getInstance().send(message.getRecipient(), payload);
        } catch (Exception e) {
            throw new Exception("SLACK channel error: " + e.getMessage());
        }
    }

}
