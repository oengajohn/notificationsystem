package com.systech.notificationsystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.systech.notificationsystem.model.enums.MessageType;
import com.systech.notificationsystem.model.enums.MessagingUserType;
import com.systech.notificationsystem.model.enums.SmsType;
import com.systech.notificationsystem.model.enums.YesNo;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sms {

    private Long id;

    private String clientNumber;

    private String clientName;

    private YesNo smsRead;

    private YesNo delivered;

    private MessageType messageType;

    private String message;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date timeCreated;

    private MessagingUserType messagingUserType;

    private Integer retryCount;

    private YesNo sendAsBatch;

    private String comments;

    private Long schemeId;

    private SmsType smsType;

    private String identifierValue;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
