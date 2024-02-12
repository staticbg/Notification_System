package com.notification.system.channel;

import com.notification.system.model.Notification;
import com.notification.system.service.MailSenderService;
import com.notification.system.utils.Constants;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;


@Service
@Getter
public class EmailChannel implements Channel {
    private final String name = "EMAIL";
    private final MailSenderService mailSenderService;

    public EmailChannel(@Autowired MailSenderService mailSenderService) {
        super();
        this.mailSenderService = mailSenderService;
    }

    @Override
    public void sendNotification(Notification notification) throws Exception {
        try {
            sendEmailNotification(notification);
        } catch (Exception e) {
            throw new Exception("Email channel error: " + e.getMessage());
        }
    }

    private void sendEmailNotification(Notification notification) {
        for (String recipient: notification.getRecipients()) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(Constants.EMAIL_FROM_ADDRESS);
            message.setTo(recipient);
            message.setSubject(notification.getSubject());
            message.setText(notification.getContent());
            mailSenderService.send(message);
        }
    }

}
