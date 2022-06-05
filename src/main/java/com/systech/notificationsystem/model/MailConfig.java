package com.systech.notificationsystem.model;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import lombok.Getter;
import lombok.Setter;

@ApplicationScoped
@Getter
@Setter
public class MailConfig {

    @Inject
    @ConfigProperty(name = "service.mail.enable")
    private boolean enable;

    @Inject
    @ConfigProperty(name = "service.mail.host")
    private String host;

    @Inject
    @ConfigProperty(name = "service.mail.port")
    private int port;

    @Inject
    @ConfigProperty(name = "service.mail.auth.username")
    private String username;

    @Inject
    @ConfigProperty(name = "service.mail.auth.password")
    private String password;

    @Inject
    @ConfigProperty(name = "service.mail.from")
    private String from;




}
