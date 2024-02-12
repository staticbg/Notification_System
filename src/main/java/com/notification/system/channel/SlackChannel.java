package com.notification.system.channel;

import com.notification.system.model.Notification;
import lombok.Getter;
import org.springframework.stereotype.Service;


@Service
@Getter
public class SlackChannel implements Channel {
    private final String name = "SLACK";

    @Override
    public boolean sendNotification(Notification notification) {
        // TODO: Implement Slack sending logic here
        System.out.println("Slack Channel sendNotification() called");
        return false;
    }
}
