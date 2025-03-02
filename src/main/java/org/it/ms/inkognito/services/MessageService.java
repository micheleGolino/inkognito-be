package org.it.ms.inkognito.services;

import org.it.ms.inkognito.entities.Message;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class MessageService {

	public Response getMessage(Long id) {
		Message message = Message.findById(id);
		if (message == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(message).build();
	}

	@Transactional
	public Response createMessage(Message message) {
		if (message != null) {
			Message existing = Message.findById(message.getId());
			if (existing == null) {
				message.persist();
				return Response.ok(message).build();
			}
			return Response.status(Response.Status.CONFLICT).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}

	@Transactional
	public Response updateMessage(Long id, Message updatedMessage) {
		Message message = Message.findById(id);
		if (message == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		message.setConversationId(updatedMessage.getConversationId());
		message.setSenderId(updatedMessage.getSenderId());
		message.setMessageText(updatedMessage.getMessageText());
		message.setSentAt(updatedMessage.getSentAt());
		message.setNote(updatedMessage.getNote());
		message.persist();
		return Response.ok(message).build();
	}

	@Transactional
	public Response deleteMessage(Long id) {
		Message message = Message.findById(id);
		if (message == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		message.delete();
		return Response.ok("Message " + id + " deleted.").build();
	}
}
