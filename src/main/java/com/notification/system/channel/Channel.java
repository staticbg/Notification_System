package com.notification.system.channel;

import com.notification.system.model.Notification;

public interface Channel {

    String getName();

    void sendNotification(Notification notification) throws Exception;
}
