package com.systech.notificationsystem.service.impl.javamail;

import com.systech.notificationsystem.model.MailConfig;
import com.systech.notificationsystem.model.MailDTO;
import com.systech.notificationsystem.model.enums.MailProvider;
import com.systech.notificationsystem.qualifier.SendEmailProvider;
import com.systech.notificationsystem.service.MailSender;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;

@SendEmailProvider(MailProvider.JAVAX_MAIL)
@Stateless
public class JavaMailSenderImpl implements MailSender {
    @Inject
    private MailConfig mailConfig;
    private Session session;
    @Inject
    private Logger log;
    @PostConstruct
    public void setup(){

        // Mention the Sender's email address
        String from = mailConfig.getFrom();

        // Mention the SMTP server address. Below Gmail's SMTP server is being used to send email
        String host = mailConfig.getHost();
        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", mailConfig.getPort());
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, mailConfig.getPassword());
            }
        });

        // Used to debug SMTP issues
        session.setDebug(true);
    }
    @Override
    public boolean sendEmail(MailDTO mailDTO) {

        // Mention the Recipient's email addess
        String to = mailDTO.getClientEmail();


        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(mailConfig.getFrom()));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(mailDTO.getMailSubject());

            // Now set the actual message
            message.setText(mailDTO.getMessage());

            log.debug("sending...");
            // Send message
            Transport.send(message);
            log.info("Sent message successfully....");
            return true;
        } catch (MessagingException mex) {
            mex.printStackTrace();
            log.error("{}",mex.getLocalizedMessage());
            return false;
        }

    }

}
