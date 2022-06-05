package com.systech.notificationsystem.service.impl.africastalking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class AfricaTalkingPostRequestData {
    private String to;
    private String message;
    private String from;
    private String username;
}
