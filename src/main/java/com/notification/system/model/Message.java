package com.notification.system.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
public class Message {
    @Id
    @GeneratedValue
    private UUID id;
    private UUID notification;
    private String recipient;
    private String subject;
    private String content;
    private String channel;
    private MessageStatus status;
    private Timestamp sentTimestamp;
}
