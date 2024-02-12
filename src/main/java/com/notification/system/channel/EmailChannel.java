package com.notification.system.channel;

import com.notification.system.model.Notification;
import lombok.Getter;
import org.springframework.stereotype.Service;


@Service
@Getter
public class EmailChannel implements Channel {
    private final String name = "EMAIL";

    @Override
    public boolean sendNotification(Notification notification) {
        // TODO: Implement Email sending logic here
        System.out.println("Email Channel sendNotification() called");
        return false;
    }
}
