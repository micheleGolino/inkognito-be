package org.it.ms.inkognito.mock;

import org.it.ms.inkognito.dto.ConversationDTO;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Consumes;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Risorsa REST mock che restituisce conversazioni fittizie.
 * Endpoint: /mock/conversations
 */
@Path("/mock/conversations")
public class MockConversationResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ConversationDTO> getMockConversations() {
        return Arrays.asList(
                new ConversationDTO(BigInteger.valueOf(1), BigInteger.valueOf(1), BigInteger.valueOf(2), "Nota 1", LocalDateTime.now().minusDays(1)),
                new ConversationDTO(BigInteger.valueOf(2), BigInteger.valueOf(2), BigInteger.valueOf(3), "Nota 2", LocalDateTime.now().minusHours(5))
        );
    }

    @GET
    @Path("/by-user1/{user1Id}")
    public List<ConversationDTO> getByUser1Id(@PathParam("user1Id") BigInteger user1Id) {
        // Restituisce sempre la lista mock filtrata per user1Id
        return getMockConversations().stream().filter(c -> c.getUser1Id().equals(user1Id)).toList();
    }

    @GET
    @Path("/by-user2/{user2Id}")
    public List<ConversationDTO> getByUser2Id(@PathParam("user2Id") BigInteger user2Id) {
        return getMockConversations().stream().filter(c -> c.getUser2Id().equals(user2Id)).toList();
    }

    @GET
    @Path("/between/{userA}/{userB}")
    public ConversationDTO getBetweenUsers(@PathParam("userA") BigInteger userA, @PathParam("userB") BigInteger userB) {
        return getMockConversations().stream().filter(c ->
            (c.getUser1Id().equals(userA) && c.getUser2Id().equals(userB)) ||
            (c.getUser1Id().equals(userB) && c.getUser2Id().equals(userA))
        ).findFirst().orElse(null);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ConversationDTO getConversationById(@PathParam("id") BigInteger id) {
        return getMockConversations().stream()
            .filter(c -> c.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ConversationDTO createConversation(ConversationDTO conversation) {
        conversation.setId(BigInteger.valueOf(999));
        return conversation;
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ConversationDTO updateConversation(@PathParam("id") BigInteger id, ConversationDTO conversation) {
        conversation.setId(id);
        return conversation;
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteConversation(@PathParam("id") BigInteger id) {
        return "Conversation eliminata (mock): " + id;
    }
}
