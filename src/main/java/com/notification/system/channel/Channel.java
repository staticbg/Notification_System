package com.notification.system.channel;

import java.util.List;

public interface Channel {
    boolean sendNotification(String notificationContent, List<String> recipients);
}