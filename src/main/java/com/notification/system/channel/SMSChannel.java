package com.notification.system.channel;

import java.util.List;

public class SMSChannel implements Channel {
    @Override
    public boolean sendNotification(String notificationContent, List<String> recipients) {
        // TODO: Implement SMS sending logic here
        return false;
    }
}
