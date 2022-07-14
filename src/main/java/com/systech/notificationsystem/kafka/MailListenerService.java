package com.systech.notificationsystem.kafka;

import com.systech.notificationsystem.model.MailDTO;
import com.systech.notificationsystem.model.enums.MailProvider;
import com.systech.notificationsystem.qualifier.SendEmailProvider;
import com.systech.notificationsystem.service.MailSender;
import com.systech.notificationsystem.util.JsonUtils;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import org.aerogear.kafka.cdi.annotation.Consumer;
import org.slf4j.Logger;

@ApplicationScoped
public class MailListenerService {
    @Inject
    @SendEmailProvider(MailProvider.JAVAX_MAIL)
    private MailSender mailSender;

    @Inject
    private Logger log;

    @Inject
    private EmailPublisherService publishEmailToFundMaster;

    @Consumer(topics = Config.TO_NS_TOPIC_EMAIL, groupId = Config.TO_NS_EMAIL_GROUP_ID_ENTITY)
    public void receiver(final JsonObject message) {
        final MailDTO mailDTO = JsonUtils.fromJson(message, MailDTO.class);
        var sent = mailSender.sendEmail(mailDTO);
        if(sent){
            //TODO: Publish to fund master
            log.info("Publishing email to fund master for update of delivery status {}",mailDTO.getClientEmail());
            publishEmailToFundMaster.sendMessage(mailDTO);
        }


    }

}