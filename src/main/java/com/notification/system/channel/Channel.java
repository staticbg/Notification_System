package com.notification.system.channel;

import com.notification.system.model.Notification;

public interface Channel {

    String getName();

    boolean sendNotification(Notification notification);
}
