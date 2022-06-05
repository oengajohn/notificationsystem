package com.systech.notificationsystem.service;

import com.systech.notificationsystem.model.Sms;

public interface SmsSender {
    boolean sendSms(Sms sms);
}
