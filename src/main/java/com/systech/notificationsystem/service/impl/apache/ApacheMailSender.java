package com.systech.notificationsystem.service.impl.apache;

import com.systech.notificationsystem.model.MailConfig;
import com.systech.notificationsystem.model.MailDTO;
import com.systech.notificationsystem.model.enums.MailProvider;
import com.systech.notificationsystem.qualifier.SendEmailProvider;
import com.systech.notificationsystem.service.MailSender;
import javax.inject.Inject;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;

@SendEmailProvider(MailProvider.APACHE_COMMONS)
public class ApacheMailSender implements MailSender {
    @Inject
    private Logger log;
    @Inject
    private MailConfig mailConfig;

    @Override
    public boolean sendEmail(MailDTO mailDTO) {
        log.info("Sending emails:=============");
        if (!mailConfig.isEnable()) {
            log.debug("Mail service is not enabled");
        }
        String to = mailDTO.getClientEmail();
        String subject = mailDTO.getMailSubject();
        String message =mailDTO.getMessage();
        try {
            log.error("Send e-mail to '{}' with subject '{}' and content={}", to, subject, message);
            HtmlEmail email = new HtmlEmail();
            email.setHostName(mailConfig.getHost());
            email.setStartTLSEnabled(true);
            email.setSmtpPort(mailConfig.getPort());
            email.setAuthenticator(new DefaultAuthenticator(mailConfig.getUsername(),mailConfig.getPassword()));
            email.setFrom(mailConfig.getFrom());
            email.setSubject(subject);
            email.setHtmlMsg(message);
            email.addTo(to);
            email.send();
            log.debug("Sent e-mail to User '{}'", to);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("e-mail could not be sent to user '{}', exception is: {}", to, e.getMessage());
            return false;
        }

    }
}
