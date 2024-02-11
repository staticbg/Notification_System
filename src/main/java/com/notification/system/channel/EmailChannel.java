package com.notification.system.channel;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
public class EmailChannel implements Channel {
    private final String name = "EMAIL";

    @Override
    public boolean sendNotification(String notificationContent, List<String> recipients) {
        // TODO: Implement Email sending logic here
        System.out.println("Email Channel sendNotification() called");
        return false;
    }
}
