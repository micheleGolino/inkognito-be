package org.it.ms.inkognito.resources;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.it.ms.inkognito.entities.Conversation;
import org.it.ms.inkognito.services.ConversationService;

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

@Path("/conversations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConversationResource {

	@Inject
	ConversationService conversationService;

	@GET
	@Operation(summary = "List all conversations", description = "Retrieve all conversations.")
	@APIResponse(responseCode = "200", description = "Conversations found")
	public List<Conversation> listConversations() {
		return Conversation.listAll(Sort.by("id"));
	}

	@GET
	@Path("/{id}")
	@Operation(summary = "Get conversation by ID", description = "Retrieve conversation details by ID.")
	@APIResponse(responseCode = "200", description = "Conversation found")
	@APIResponse(responseCode = "404", description = "Conversation not found")
	public Response getConversation(@PathParam("id") Long id) {
		return conversationService.getConversation(id);
	}

	@POST
	@Operation(summary = "Create a new conversation", description = "Create a new conversation.")
	@APIResponse(responseCode = "201", description = "Conversation created")
	@APIResponse(responseCode = "409", description = "Conversation already exists")
	public Response createConversation(Conversation conversation) {
		return conversationService.createConversation(conversation);
	}

	@PUT
	@Path("/{id}")
	@Operation(summary = "Update conversation", description = "Update an existing conversation.")
	@APIResponse(responseCode = "200", description = "Conversation updated")
	@APIResponse(responseCode = "404", description = "Conversation not found")
	public Response updateConversation(@PathParam("id") Long id, Conversation updatedConversation) {
		return conversationService.updateConversation(id, updatedConversation);
	}

	@DELETE
	@Path("/{id}")
	@Operation(summary = "Delete conversation", description = "Delete a conversation by ID.")
	@APIResponse(responseCode = "200", description = "Conversation deleted")
	@APIResponse(responseCode = "404", description = "Conversation not found")
	public Response deleteConversation(@PathParam("id") Long id) {
		return conversationService.deleteConversation(id);
	}
}
