// Package mock: risorsa per simulare un feed di utenti suggeriti.
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
 * Risorsa REST mock che restituisce un feed di utenti suggeriti.
 * Endpoint: /mock/feed
 */
@Path("/mock/feed")
public class MockFeedResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDTO> getFeed() {
        return Arrays.asList(
            new UserDTO(
                java.math.BigInteger.valueOf(4),
                "dario@example.com",
                "Dario",
                "Blu",
                "dario",
                "Appassionato di cucina.",
                "https://randomuser.me/api/portraits/men/4.jpg",
                null,
                null
            ),
            new UserDTO(
                java.math.BigInteger.valueOf(5),
                "elena@example.com",
                "Elena",
                "Rosa",
                "elena",
                "Amo la natura e gli animali.",
                "https://randomuser.me/api/portraits/women/5.jpg",
                null,
                null
            )
        );
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO getUserById(@PathParam("id") Long id) {
        return getFeed().stream()
            .filter(u -> u.getId().longValue() == id)
            .findFirst()
            .orElse(null);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserDTO createUser(UserDTO user) {
        user.setId(java.math.BigInteger.valueOf(9999));
        return user;
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserDTO updateUser(@PathParam("id") Long id, UserDTO user) {
        user.setId(java.math.BigInteger.valueOf(id));
        return user;
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteUser(@PathParam("id") Long id) {
        return "Utente eliminato (mock feed): " + id;
    }
}
