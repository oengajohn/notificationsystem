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

    @Inject
    private SmsPublisherService publishSmsToFundMaster;

    @Consumer(
            topics = Config.TO_NS_TOPIC_SMS,
            groupId = Config.TO_NS_SMS_GROUP_ID_ENTITY
    )
    public void receiver(final JsonObject message) {
        final Sms sms = JsonUtils.fromJson(message, Sms.class);
        var sent = smsSender.sendSms(sms);
        if (sent) {
            //TODO: publish to fund master
            log.info("Publishing sms to fund master for update of delivery status {}",sms.getClientNumber());
            publishSmsToFundMaster.sendMessage(sms);

        }


    }

}