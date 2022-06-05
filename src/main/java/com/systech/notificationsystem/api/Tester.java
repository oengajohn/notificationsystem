package com.systech.notificationsystem.api;

import com.systech.notificationsystem.model.ManageResponse;
import java.util.List;
import java.util.stream.Stream;
import javax.enterprise.context.RequestScoped;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.stream.JsonCollectors;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;


@RequestScoped
@Path("/v1")
public class Tester {

    @Timed(name = "getArticlesTimed",
            absolute = true,
            displayName = "web-api /getmultiple timer",
            description = "Time taken by com.ibm.webapi.apis.GetArticles.getArticles")
    @Counted(name = "getArticlesCounted",
            absolute = true,
            displayName = "web-api /getmultiple count",
            description = "Number of times com.ibm.webapi.apis.GetArticles.getArticles has been invoked")
    @Metered(name = "getArticlesMetered",
            displayName = "web-api /getmultiple frequency",
            description = "Rate the throughput of com.ibm.webapi.apis.GetArticles.getArticles")
    @GET
    @Path("/getmultiple")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Get most recently added articles", content =
            @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.ARRAY, implementation =
                    ManageResponse.class))),
            @APIResponse(responseCode = "500", description = "Internal service error") })
    @Operation(summary = "Get most recently added articles", description = "Get most recently added articles")
    public Response getArticles() {
        ManageResponse manage = new ManageResponse();
        try {
            List<ManageResponse> articles = List.of(manage);
            return Response.ok().entity(articles).build();
        }
        catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
