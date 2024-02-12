/**
 * Email notification channel implementation
 */
package com.notification.system.channel;

import com.notification.system.model.Message;
import com.notification.system.service.MailSenderService;
import com.notification.system.utils.Constants;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;


@Service
@Getter
@RequiredArgsConstructor
@Slf4j
public class EmailChannel implements Channel {
    private final String name = "EMAIL";
    private final MailSenderService mailSenderService;


    /**
     * Method which sends notification via the Email channel
     */
    @Override
    public void sendNotification(Message message) throws Exception {
        log.info("Sending notification for message {} via Email channel", message.getId());
        SimpleMailMessage emailMessage = new SimpleMailMessage();
        emailMessage.setFrom(Constants.EMAIL_FROM_ADDRESS);
        emailMessage.setTo(message.getRecipient());
        emailMessage.setSubject(message.getSubject());
        emailMessage.setText(message.getContent());
        try {
            mailSenderService.send(emailMessage);
            log.info("Successfully sent notification for message {} via Email channel", message.getId());
        } catch (Exception e) {
            log.error("EMAIL channel error for message {}: {}", message.getId(), e.getMessage());
            throw new Exception("EMAIL channel error: " + e.getMessage());
        }
    }

}
