
package com.systech.notificationsystem.service.impl.africastalking;

import java.util.List;
import javax.json.bind.annotation.JsonbProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SMSMessageData {

    @JsonbProperty("Message")
    private String message;

    @JsonbProperty("Recipients")
    private List<Recipient> recipients;


}
