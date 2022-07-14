
package com.systech.notificationsystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.systech.notificationsystem.model.enums.MessageType;
import com.systech.notificationsystem.model.enums.MessagingUserType;
import com.systech.notificationsystem.model.enums.YesNo;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailDTO {
    private Long id;

    private String attach;

    private String clientEmail;

    private String clientName;

    private YesNo delivered;

    private YesNo emailRead;

    private String mailFrom;

    private YesNo fastTrack;

    private String mailSubject;

    private String message;

    private MessageType messageType;

    private Integer retryCount;

    private Long schemeId;

    private YesNo sendAsBatch;

    private String senderName;

    private Long smtpId;

    private Long templateId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date timeCreated;

    private MessagingUserType messagingUserType;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
