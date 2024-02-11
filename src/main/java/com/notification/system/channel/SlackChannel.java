package com.notification.system.channel;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
public class SlackChannel implements Channel {
    private final String name = "SLACK";

    @Override
    public boolean sendNotification(String notificationContent, List<String> recipients) {
        // TODO: Implement Slack sending logic here
        System.out.println("Slack Channel sendNotification() called");
        return false;
    }
}
