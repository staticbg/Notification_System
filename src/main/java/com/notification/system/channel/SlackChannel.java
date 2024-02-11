package com.notification.system.channel;

import java.util.List;

public class SlackChannel implements Channel {
    @Override
    public boolean sendNotification(String notificationContent, List<String> recipients) {
        // TODO: Implement Slack sending logic here
        return false;
    }
}
