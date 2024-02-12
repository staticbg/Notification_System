package com.notification.system.channel;

import com.notification.system.model.Message;

public interface Channel {

    String getName();

    void sendNotification(Message message) throws Exception;
}
