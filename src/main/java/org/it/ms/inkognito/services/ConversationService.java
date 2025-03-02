package org.it.ms.inkognito.services;

import org.it.ms.inkognito.entities.Conversation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class ConversationService {

	public Response getConversation(Long id) {
		Conversation conversation = Conversation.findById(id);
		if (conversation == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(conversation).build();
	}

	@Transactional
	public Response createConversation(Conversation conversation) {
		if (conversation != null) {
			Conversation existing = Conversation.findById(conversation.getId());
			if (existing == null) {
				conversation.persist();
				return Response.ok(conversation).build();
			}
			return Response.status(Response.Status.CONFLICT).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}

	@Transactional
	public Response updateConversation(Long id, Conversation updatedConversation) {
		Conversation conversation = Conversation.findById(id);
		if (conversation == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		conversation.setUser1Id(updatedConversation.getUser1Id());
		conversation.setUser2Id(updatedConversation.getUser2Id());
		conversation.setNote(updatedConversation.getNote());
		conversation.persist();
		return Response.ok(conversation).build();
	}

	@Transactional
	public Response deleteConversation(Long id) {
		Conversation conversation = Conversation.findById(id);
		if (conversation == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		conversation.delete();
		return Response.ok("Conversation " + id + " deleted.").build();
	}
}
