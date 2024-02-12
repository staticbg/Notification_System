package com.notification.system.utils;

public class Constants {
    public static final String VALIDATION_ERRORS_RESPONSE_KEY = "validation_errors";
    public static final String INTERNAL_SERVER_ERROR_RESPONSE_KEY = "internal_server_errors";
    public static final String NOTIFICATION_SYSTEM_SOURCE_ADDRESS = "Notification System";
    public static final String EMAIL_FROM_ADDRESS = "noreply@notificationsystem.com";
    public static final String TRIGGER_NOTIFICATION_SENDING_KAFKA_TOPIC = "unprocessed_messages";
    public static final String KAFKA_GROUP_ID = "notification-system-group";
}
