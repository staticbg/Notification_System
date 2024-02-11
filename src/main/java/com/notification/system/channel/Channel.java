package com.notification.system.channel;

import java.util.List;

public interface Channel {

    String getName();

    boolean sendNotification(String notificationContent, List<String> recipients);
}