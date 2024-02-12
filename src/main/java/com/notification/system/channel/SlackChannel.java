package com.notification.system.channel;

import com.notification.system.model.Notification;
import lombok.Getter;
import org.springframework.stereotype.Service;


@Service
@Getter
public class SlackChannel implements Channel {
    private final String name = "SLACK";

    @Override
    public void sendNotification(Notification notification) throws Exception {
        try {
            // TODO: Implement Slack sending logic here
            System.out.println("Slack Channel sendNotification() called");
        } catch (Exception e) {
            throw new Exception("Email channel error: " + e.getMessage());
        }

    }
}
