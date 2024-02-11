package com.notification.system.model;

import lombok.Data;

import java.util.List;


@Data
public class Notification {
    private String content;
    private List<String> recipients;
    private List<String> channels;
}
