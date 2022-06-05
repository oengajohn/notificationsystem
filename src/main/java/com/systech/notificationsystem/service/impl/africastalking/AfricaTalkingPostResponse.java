
package com.systech.notificationsystem.service.impl.africastalking;




import javax.json.bind.annotation.JsonbProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AfricaTalkingPostResponse {

    @JsonbProperty("SMSMessageData")
    private SMSMessageData sMSMessageData;


}
