package com.notification.system.channel;

import java.util.List;

public class EmailChannel implements Channel {
    @Override
    public boolean sendNotification(String notificationContent, List<String> recipients) {
        // TODO: Implement Email sending logic here
        return false;
    }
}
