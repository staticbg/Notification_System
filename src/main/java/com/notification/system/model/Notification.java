package com.notification.system.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;


@Data
public class Notification {
    @NotBlank(message="Notification subject cannot be empty")
    private String subject;
    @NotBlank(message="Notification content cannot be empty")
    private String content;
    @NotEmpty(message="Notification must have recipients")
    private List<@NotBlank(message="Notification recipients cannot be empty values") String> recipients;
    @NotEmpty
    // TODO: The validation of channel names should be without hardcoding..
    private List<@Pattern(regexp="EMAIL|SMS|SLACK", message="Supported notification channels are EMAIL, SMS and Slack") String> channels;
}
