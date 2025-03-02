package org.it.ms.inkognito.resources;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.it.ms.inkognito.entities.Message;
import org.it.ms.inkognito.services.MessageService;

import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {

    @Inject
    MessageService messageService;

    @GET
    @Operation(summary = "List all messages", description = "Retrieve all messages.")
    @APIResponse(responseCode = "200", description = "Messages found")
    public List<Message> listMessages() {
        return Message.listAll(Sort.by("id"));
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get message by ID", description = "Retrieve message details by ID.")
    @APIResponse(responseCode = "200", description = "Message found")
    @APIResponse(responseCode = "404", description = "Message not found")
    public Response getMessage(@PathParam("id") Long id) {
        return messageService.getMessage(id);
    }

    @POST
    @Operation(summary = "Create a new message", description = "Create a new message.")
    @APIResponse(responseCode = "201", description = "Message created")
    @APIResponse(responseCode = "409", description = "Message already exists")
    public Response createMessage(Message message) {
        return messageService.createMessage(message);
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Update message", description = "Update an existing message.")
    @APIResponse(responseCode = "200", description = "Message updated")
    @APIResponse(responseCode = "404", description = "Message not found")
    public Response updateMessage(@PathParam("id") Long id, Message updatedMessage) {
        return messageService.updateMessage(id, updatedMessage);
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete message", description = "Delete a message by ID.")
    @APIResponse(responseCode = "200", description = "Message deleted")
    @APIResponse(responseCode = "404", description = "Message not found")
    public Response deleteMessage(@PathParam("id") Long id) {
        return messageService.deleteMessage(id);
    }
}
