/**
 * REST controller for Notification creation and other endpoints
 */

package com.notification.system.rest;

import com.notification.system.model.Message;
import com.notification.system.model.NotificationRequest;
import com.notification.system.service.NotificationSendingService;
import com.notification.system.service.NotificationService;
import com.notification.system.utils.Constants;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;
    private final NotificationSendingService notificationSendingService;

    @PostMapping(path = "/notification", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, List<String>>> createNotification(@Valid @RequestBody NotificationRequest notificationRequest, Errors errors) {
        if (errors.hasErrors()) {
            List<String> errorMessages = new ArrayList<>();
            for (ObjectError error: errors.getAllErrors()) {
                errorMessages.add(error.getDefaultMessage());
            }
            Map<String, List<String>> errorResponse = new HashMap<>();
            errorResponse.put(Constants.VALIDATION_ERRORS_RESPONSE_KEY, errorMessages);

            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            notificationService.createNotification(notificationRequest);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of(
                    Constants.INTERNAL_SERVER_ERROR_RESPONSE_KEY, List.of(e.getMessage())
            ), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/messages/{notificationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Message> getMessagesForNotification(@PathVariable UUID notificationId) {
        return notificationService.getMessagesForNotification(notificationId);
    }

    @GetMapping(path = "/messages/unprocessed", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Message> getUnprocessedMessages() {
        return notificationService.getUnprocessedMessages();
    }

    @PostMapping(path = "/triggerNotificationSending", produces = MediaType.APPLICATION_JSON_VALUE)
    public void triggerNotificationSending() {
        notificationSendingService.triggerNotificationSending();
    }

}
