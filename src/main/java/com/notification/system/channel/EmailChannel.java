package com.notification.system.channel;

import com.notification.system.model.Message;
import com.notification.system.service.MailSenderService;
import com.notification.system.utils.Constants;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;


@Service
@Getter
@RequiredArgsConstructor
public class EmailChannel implements Channel {
    private final String name = "EMAIL";
    private final MailSenderService mailSenderService;


    @Override
    public void sendNotification(Message message) throws Exception {
        SimpleMailMessage emailMessage = new SimpleMailMessage();
        emailMessage.setFrom(Constants.EMAIL_FROM_ADDRESS);
        emailMessage.setTo(message.getRecipient());
        emailMessage.setSubject(message.getSubject());
        emailMessage.setText(message.getContent());
        try {
            mailSenderService.send(emailMessage);
        } catch (Exception e) {
            throw new Exception("EMAIL channel error: " + e.getMessage());
        }
    }

}
