package com.systech.notificationsystem.service.impl.africastalking;

import com.systech.notificationsystem.model.Sms;
import com.systech.notificationsystem.model.SmsListConfig;
import com.systech.notificationsystem.model.enums.SmsProvider;
import com.systech.notificationsystem.model.enums.YesNo;
import com.systech.notificationsystem.qualifier.SendSmsProvider;
import com.systech.notificationsystem.service.SmsSender;
import com.systech.notificationsystem.util.Util;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import org.slf4j.Logger;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@SendSmsProvider(SmsProvider.AFRICASTALKING)
public class AfricaTalkingGateway implements SmsSender {


    @Inject
    private SmsListConfig config;

    @Inject
    private Logger log;

    private WebTarget target;


    @PostConstruct
    public void setup() {
        Client client = ClientBuilder.newClient();
        target = client.target(config.getExternalApiUrl());
    }

    @Override
    public boolean sendSms(Sms sms) {
        try {
            AfricaTalkingPostResponse africaTalkingPostResponse;
            if (sms.getClientNumber() != null) {
                String phone = Util.sanitizePhoneNumber(sms.getClientNumber(), config.getCountryCode());
                africaTalkingPostResponse = sendMessage(phone, sms.getMessage(), config.getUsernamePlaceHolder(),
                        config.getExternalApiUsername());
                var recipients = africaTalkingPostResponse.getSMSMessageData().getRecipients();
                if (recipients != null) {
                    for (Recipient recipient : recipients) {
                        if (recipient.getStatus().equalsIgnoreCase("success")) {
                            sms.setDelivered(YesNo.YES);
                            sms.setComments("Sent");
                        } else {
                            sms.setDelivered(YesNo.NO);
                            sms.setRetryCount(sms.getRetryCount() == null ? 0 : sms.getRetryCount() + 1);
                        }
                        //TODO: save sms to db
                        // sms = dao.saveOrUpdate(sms);
                    }
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    private AfricaTalkingPostResponse sendMessage(String to, String message, String from, String username) {
        Form form = new Form();
        form.param("to", to);
        form.param("message", message);
        form.param("from", from);
        form.param("username", username);
        return target.request(APPLICATION_JSON)
                .header("apiKey", config.getExternalApiPassword())
                .post(Entity.entity(form, APPLICATION_FORM_URLENCODED_TYPE), AfricaTalkingPostResponse.class);


    }

}
