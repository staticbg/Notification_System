package com.notification.system.channel;

import com.notification.system.model.Notification;
import lombok.Getter;
import org.springframework.stereotype.Service;


@Service
@Getter
public class SMSChannel implements Channel {
    private final String name = "SMS";

    @Override
    public boolean sendNotification(Notification notification) {
        // TODO: Implement SMS sending logic here
        System.out.println("SMS Channel sendNotification() called");
        return false;
    }
}
