package com.notification.system.rest;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.notification.system.model.Notification;
import com.notification.system.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(@Autowired NotificationService notificationService) {
        super();
        this.notificationService = notificationService;
    }

    // TODO: Validate the notification - notification channels should exist!
    @PostMapping("/notification")
    public void createNotification(@RequestBody Notification notification) {
        notificationService.sendNotification(notification);
    }
}
