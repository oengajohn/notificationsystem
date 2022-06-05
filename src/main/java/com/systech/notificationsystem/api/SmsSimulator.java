package com.systech.notificationsystem.api;

import com.systech.notificationsystem.kafka.EmailPublisherService;
import com.systech.notificationsystem.kafka.SmsPublisherService;
import com.systech.notificationsystem.model.MailDTO;
import com.systech.notificationsystem.model.Sms;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/v1/")
@Tag(name = "Config Retrieval service", description = "Get the value for a certain config")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class SmsSimulator {
    @Inject
    private SmsPublisherService smsPublisherService;

    @Inject
    private EmailPublisherService emailPublisherService;
    @POST
    @Path("/send-sms")
    @Operation(
            summary = "Send an sms object to apache kafka",
            description = "Send an sms object to apache kafka")
    @APIResponse(
            responseCode = "201",
            description = "Sms sent Successfully",
            content = @Content(mediaType = "application/json"))
    public Response sendSms(Sms sms) {
        for(int i=0;i<5;i++) {
            smsPublisherService.sendMessage(sms);
        }
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("/send-mail")
    @Operation(
            summary = "Send an email object to apache kafka",
            description = "Send an email object to apache kafka")
    @APIResponse(
            responseCode = "201",
            description = "email sent Successfully",
            content = @Content(mediaType = "application/json"))
    public Response sendEmail(MailDTO mailDTO) {
        for(int i=0;i<3000;i++) {
            mailDTO.setMessage("Hello"+i);
            emailPublisherService.sendMessage(mailDTO);
        }
        return Response.status(Response.Status.OK).build();
    }



}
