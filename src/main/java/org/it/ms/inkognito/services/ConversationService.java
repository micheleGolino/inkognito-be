package org.it.ms.inkognito.services;

import org.it.ms.inkognito.entities.Conversation;
import org.it.ms.inkognito.dto.ConversationDTO;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

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

	public List<ConversationDTO> findByUser1Id(BigInteger user1Id) {
		return Conversation.list("user1Id", user1Id)
				.stream()
				.map(c -> toDTO((Conversation) c))
				.collect(Collectors.toList());
	}

	public List<ConversationDTO> findByUser2Id(BigInteger user2Id) {
		return Conversation.list("user2Id", user2Id)
				.stream()
				.map(c -> toDTO((Conversation) c))
				.collect(Collectors.toList());
	}

	public ConversationDTO findBetweenUsers(BigInteger userA, BigInteger userB) {
		return Conversation.find("Conversation.findBetweenUsers", 
				java.util.Map.of("userA", userA, "userB", userB))
				.firstResultOptional()
				.map(c -> toDTO((Conversation) c))
				.orElse(null);
	}

	private ConversationDTO toDTO(Conversation c) {
		return new ConversationDTO(
			c.getId(),
			c.getUser1Id(),
			c.getUser2Id(),
			c.getNote(),
			c.getDateInsert()
		);
	}
}
