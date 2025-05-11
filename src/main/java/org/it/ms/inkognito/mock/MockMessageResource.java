// Package dedicato alle risorse mock per facilitare lo sviluppo e il test delle API mobile.
package org.it.ms.inkognito.mock;

import org.it.ms.inkognito.dto.MessageDTO;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Consumes;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Risorsa REST mock che restituisce una lista di messaggi fittizi.
 * Utile per testare la messaggistica mobile senza database.
 * Endpoint: /mock/messages
 */
@Path("/mock/messages")
public class MockMessageResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MessageDTO> getMockMessages() {
        return Arrays.asList(
                new MessageDTO(1L, 1L, 2L, "Ciao Bob!", LocalDateTime.now().minusMinutes(10)),
                new MessageDTO(2L, 2L, 1L, "Ciao Alice! Come va?", LocalDateTime.now().minusMinutes(8)),
                new MessageDTO(3L, 3L, 1L, "Ehi Alice, ci vediamo stasera?", LocalDateTime.now().minusMinutes(2))
        );
    }

    @GET
    @Path("/by-conversation/{conversationId}")
    public List<MessageDTO> getByConversationId(@PathParam("conversationId") Long conversationId) {
        return getMockMessages().stream().filter(m -> m.getReceiverId().equals(conversationId)).toList();
    }

    @GET
    @Path("/by-sender/{senderId}")
    public List<MessageDTO> getBySenderId(@PathParam("senderId") Long senderId) {
        return getMockMessages().stream().filter(m -> m.getSenderId().equals(senderId)).toList();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MessageDTO getMessageById(@jakarta.ws.rs.PathParam("id") Long id) {
        return getMockMessages().stream()
            .filter(m -> m.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MessageDTO createMessage(MessageDTO message) {
        message.setId(999L);
        return message;
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MessageDTO updateMessage(@jakarta.ws.rs.PathParam("id") Long id, MessageDTO message) {
        message.setId(id);
        return message;
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteMessage(@jakarta.ws.rs.PathParam("id") Long id) {
        return "Messaggio eliminato (mock): " + id;
    }
}
