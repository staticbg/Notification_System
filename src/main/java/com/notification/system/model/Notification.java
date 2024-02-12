package com.notification.system.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="notifications")
@Data
public class Notification {
    @Id
    @GeneratedValue
    private UUID id;
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "notification")
    private List<Message> messages;
}
