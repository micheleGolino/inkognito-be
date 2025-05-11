package org.it.ms.inkognito.mock;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.it.ms.inkognito.dto.InterestDTO;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * Risorsa REST mock che restituisce interessi fittizi.
 * Endpoint: /mock/interests
 */
@Path("/mock/interests")
public class MockInterestResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<InterestDTO> getMockInterests() {
        return Arrays.asList(
                new InterestDTO(BigInteger.valueOf(1), "Sport", "Attività sportive", "", LocalDateTime.now().minusDays(10)),
                new InterestDTO(BigInteger.valueOf(2), "Musica", "Concerti e strumenti", "", LocalDateTime.now().minusDays(5))
        );
    }

    @GET
    @Path("/by-name/{name}")
    public List<InterestDTO> getByName(@PathParam("name") String name) {
        return getMockInterests().stream().filter(i -> i.getName().equalsIgnoreCase(name)).toList();
    }

    @GET
    @Path("/ordered")
    public List<InterestDTO> getAllOrdered() {
        return getMockInterests().stream().sorted(java.util.Comparator.comparing(InterestDTO::getName)).toList();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public InterestDTO getInterestById(@jakarta.ws.rs.PathParam("id") java.math.BigInteger id) {
        return getMockInterests().stream()
            .filter(i -> i.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    @jakarta.ws.rs.POST
    @Produces(MediaType.APPLICATION_JSON)
    @jakarta.ws.rs.Consumes(MediaType.APPLICATION_JSON)
    public InterestDTO createInterest(InterestDTO interest) {
        interest.setId(java.math.BigInteger.valueOf(999));
        return interest;
    }

    @jakarta.ws.rs.PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @jakarta.ws.rs.Consumes(MediaType.APPLICATION_JSON)
    public InterestDTO updateInterest(@jakarta.ws.rs.PathParam("id") java.math.BigInteger id, InterestDTO interest) {
        interest.setId(id);
        return interest;
    }

    @jakarta.ws.rs.DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteInterest(@jakarta.ws.rs.PathParam("id") java.math.BigInteger id) {
        return "Interest eliminato (mock): " + id;
    }
}
