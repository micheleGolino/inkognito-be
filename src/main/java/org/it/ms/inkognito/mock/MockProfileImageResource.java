// Package mock: risorsa per simulare l'upload di immagini profilo.
package org.it.ms.inkognito.mock;

import org.it.ms.inkognito.dto.SimpleResponseDTO;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;

/**
 * Risorsa REST mock per simulare l'upload di immagini profilo.
 * Endpoint: /mock/upload-profile-image
 */
@Path("/mock/upload-profile-image")
public class MockProfileImageResource {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public SimpleResponseDTO uploadImage() {
        // Simula una risposta di upload
        return new SimpleResponseDTO("https://randomuser.me/api/portraits/men/99.jpg");
    }
}
