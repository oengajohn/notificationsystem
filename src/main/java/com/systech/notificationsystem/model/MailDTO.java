
package com.systech.notificationsystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    private String delivered;

    private String emailRead;

    private String mailFrom;

    private String fastTrack;

    private String mailSubject;

    private String message;

    private String messageType;

    private Integer retryCount;

    private Long schemeId;

    private String sendAsBatch;

    private String senderName;

    private Long smtpId;

    private Long templateId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date timeCreated;

    private String messagingUserType;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
