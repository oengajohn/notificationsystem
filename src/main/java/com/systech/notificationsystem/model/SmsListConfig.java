
package com.systech.notificationsystem.model;


import javax.inject.Inject;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmsListConfig {
    @Inject
    @ConfigProperty(name = "externalApiUrl")
    private String externalApiUrl;

    @Inject
    @ConfigProperty(name = "externalApiUsername")
    private String externalApiUsername;

    @Inject
    @ConfigProperty(name = "externalApiPassword")
    private String externalApiPassword;

    @Inject
    @ConfigProperty(name = "countryCode")
    private String countryCode;


    @Inject
    @ConfigProperty(name = "usernamePlaceHolder")
    private String usernamePlaceHolder;


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
