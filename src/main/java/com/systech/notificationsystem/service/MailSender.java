package com.systech.notificationsystem.service;

import com.systech.notificationsystem.model.MailDTO;

public interface MailSender {
    boolean sendEmail(MailDTO mailDTO);
}
