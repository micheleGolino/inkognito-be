// Package mock: risorsa per simulare lo swipe (like/dislike) tra utenti.
package org.it.ms.inkognito.mock;

import org.it.ms.inkognito.dto.SimpleResponseDTO;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;

/**
 * Risorsa REST mock per simulare swipe (like/dislike) tra utenti.
 * Endpoint: /mock/swipe
 */
@Path("/mock/swipe")
public class MockSwipeResource {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SimpleResponseDTO swipe() {
        // Simula una risposta di swipe
        return new SimpleResponseDTO("swipe-registrato");
    }
}
