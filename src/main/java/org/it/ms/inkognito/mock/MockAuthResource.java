// Package dedicato alle risorse mock per facilitare lo sviluppo e il test delle API mobile.
package org.it.ms.inkognito.mock;

import org.it.ms.inkognito.dto.SimpleResponseDTO;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;

/**
 * Risorsa REST mock che simula un login di successo.
 * Utile per testare l'autenticazione mobile senza backend reale.
 * Endpoint: /mock/login
 */
@Path("/mock/login")
public class MockAuthResource {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SimpleResponseDTO login() {
        // Simula un login di successo
        return new SimpleResponseDTO("mock-token-12345");
    }
}
