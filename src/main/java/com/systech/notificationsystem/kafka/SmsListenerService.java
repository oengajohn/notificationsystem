package com.systech.notificationsystem.kafka;

import com.systech.notificationsystem.model.Sms;
import com.systech.notificationsystem.model.enums.SmsProvider;
import com.systech.notificationsystem.qualifier.SendSmsProvider;
import com.systech.notificationsystem.service.SmsSender;
import com.systech.notificationsystem.util.JsonUtils;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import org.aerogear.kafka.cdi.annotation.Consumer;
import org.slf4j.Logger;

@ApplicationScoped
public class SmsListenerService {
    @Inject
    @SendSmsProvider(SmsProvider.AFRICASTALKING)
    private SmsSender smsSender;

    @Inject
    private Logger log;

    @Consumer(topics = Config.SMS_TOPIC_ENTITY, groupId = Config.SMS_GROUP_ID_ENTITY)
    public void receiver(final JsonObject message) {
        final Sms sms = JsonUtils.fromJson(message, Sms.class);
        System.out.println("That's what I got from the entity: " + sms.getClientNumber());
        var sent = smsSender.sendSms(sms);
        if(sent){
            //TODO: save the sent sms
            log.info("Saving to db");
        }


    }

}