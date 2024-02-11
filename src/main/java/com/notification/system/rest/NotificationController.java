package com.notification.system.rest;

import com.notification.system.model.Notification;
import com.notification.system.service.NotificationService;
import com.notification.system.utils.Constants;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(@Autowired NotificationService notificationService) {
        super();
        this.notificationService = notificationService;
    }

    @PostMapping(path = "/notification", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, List<String>>> createNotification(@Valid @RequestBody Notification notification, Errors errors) {
        if (errors.hasErrors()) {
            List<String> errorMessages = new ArrayList<>();
            for (ObjectError error: errors.getAllErrors()) {
                errorMessages.add(error.getDefaultMessage());
            }
            Map<String, List<String>> errorResponse = new HashMap<>();
            errorResponse.put(Constants.VALIDATION_ERRORS_RESPONSE_KEY, errorMessages);

            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        notificationService.sendNotification(notification);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
