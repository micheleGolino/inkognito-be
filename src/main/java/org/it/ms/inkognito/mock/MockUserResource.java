// Package dedicato alle risorse mock per facilitare lo sviluppo e il test delle API mobile.
// Spostare qui tutte le risorse mock, documentandole per chiarezza.
package org.it.ms.inkognito.mock;

import org.it.ms.inkognito.dto.UserDTO;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.PathParam;
import java.util.Arrays;
import java.util.List;

/**
 * Risorsa REST mock che restituisce una lista di utenti fittizi.
 * Utile per lo sviluppo mobile senza dipendenza dal database.
 * Endpoint: /mock/users
 */
@Path("/mock/users")
public class MockUserResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDTO> getMockUsers() {
        return Arrays.asList(
            new UserDTO(
                java.math.BigInteger.valueOf(1),
                "alice@example.com",
                "Alice",
                "Rossi",
                "alice",
                "Ciao, sono Alice!",
                "https://randomuser.me/api/portraits/women/1.jpg",
                null,
                null
            ),
            new UserDTO(
                java.math.BigInteger.valueOf(2),
                "bob@example.com",
                "Bob",
                "Bianchi",
                "bob",
                "Amo viaggiare e leggere.",
                "https://randomuser.me/api/portraits/men/2.jpg",
                null,
                null
            ),
            new UserDTO(
                java.math.BigInteger.valueOf(3),
                "carla@example.com",
                "Carla",
                "Verdi",
                "carla",
                "Sportiva e solare.",
                "https://randomuser.me/api/portraits/women/3.jpg",
                null,
                null
            )
        );
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO getUserById(@PathParam("id") Long id) {
        // Mock: restituisce il primo utente se id==1, secondo se id==2, ecc., null altrimenti
        return getMockUsers().stream()
            .filter(u -> u.getId().longValue() == id)
            .findFirst()
            .orElse(null);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserDTO createUser(UserDTO user) {
        // Mock: restituisce l'utente ricevuto con id fittizio
        user.setId(java.math.BigInteger.valueOf(999));
        return user;
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserDTO updateUser(@PathParam("id") Long id, UserDTO user) {
        // Mock: restituisce l'utente aggiornato
        user.setId(java.math.BigInteger.valueOf(id));
        return user;
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteUser(@PathParam("id") Long id) {
        // Mock: restituisce conferma eliminazione
        return "Utente eliminato (mock): " + id;
    }
}
