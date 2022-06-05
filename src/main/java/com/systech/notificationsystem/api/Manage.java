package com.systech.notificationsystem.api;

import com.systech.notificationsystem.model.ManageResponse;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.slf4j.Logger;

@Path("/v1")
public class Manage {

    @Inject
    private Logger log;

    @POST
    @Path("/manage")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Manage application",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ManageResponse.class))),
            @APIResponse(responseCode = "401", description = "Not authorized")})
    @Operation(summary = "Manage application", description = "Manage application")
    public Response manage() {
            JsonObject output = Json.createObjectBuilder()
                    .add("message", "success")
                    .build();
            return Response
                    .ok(output).build();

    }
}