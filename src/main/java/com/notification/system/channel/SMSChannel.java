package com.notification.system.channel;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
public class SMSChannel implements Channel {
    private final String name = "SMS";

    @Override
    public boolean sendNotification(String notificationContent, List<String> recipients) {
        // TODO: Implement SMS sending logic here
        System.out.println("SMS Channel sendNotification() called");
        return false;
    }
}
