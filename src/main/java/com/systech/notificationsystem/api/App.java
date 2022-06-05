package com.systech.notificationsystem.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;

@ApplicationPath("/api")
@OpenAPIDefinition(info
        = @Info(
        title = "Web-API Service",
        version = "1.0",
        description = "Web-API Service APIs",
        contact = @Contact(
                url = "https://systechafrica.github.io/",
                name = "Systech Limited"),
        license = @License(name = "License",
                url = "https://github.com/nheidloff/cloud-native-starter/blob/master/LICENSE")))
public class App extends Application {

}