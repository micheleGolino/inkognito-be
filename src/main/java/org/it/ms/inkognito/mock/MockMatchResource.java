// Package dedicato alle risorse mock per facilitare lo sviluppo e il test delle API mobile.
package org.it.ms.inkognito.mock;

import org.it.ms.inkognito.dto.MatchDTO;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

/**
 * Risorsa REST mock che restituisce una lista di match fittizi tra utenti.
 * Utile per simulare la logica di matching nell'app mobile.
 * Endpoint: /mock/matches
 */
@Path("/mock/matches")
public class MockMatchResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MatchDTO> getMockMatches() {
        return Arrays.asList(
                new MatchDTO(1L, 2L, "2025-05-10T18:00:00"),
                new MatchDTO(1L, 3L, "2025-05-11T09:30:00")
        );
    }
}
