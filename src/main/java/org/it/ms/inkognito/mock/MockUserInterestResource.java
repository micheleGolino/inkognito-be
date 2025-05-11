package org.it.ms.inkognito.mock;

import org.it.ms.inkognito.dto.UserInterestDTO;
import org.it.ms.inkognito.dto.UserInterestIdDTO;

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
 * Risorsa REST mock che restituisce associazioni utente-interesse fittizie.
 * Endpoint: /mock/user-interests
 */
@Path("/mock/user-interests")
public class MockUserInterestResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserInterestDTO> getMockUserInterests() {
        return Arrays.asList(
                new UserInterestDTO(new UserInterestIdDTO(BigInteger.valueOf(1), BigInteger.valueOf(1)), "", LocalDateTime.now().minusDays(10), LocalDateTime.now().minusDays(5), "system", "admin"),
                new UserInterestDTO(new UserInterestIdDTO(BigInteger.valueOf(2), BigInteger.valueOf(2)), "", LocalDateTime.now().minusDays(8), LocalDateTime.now().minusDays(3), "system", "admin")
        );
    }

    @GET
    @Path("/by-user/{userId}")
    public List<UserInterestDTO> getByUserId(@PathParam("userId") BigInteger userId) {
        return getMockUserInterests().stream().filter(ui -> ui.getId().getUserId().equals(userId)).toList();
    }

    @GET
    @Path("/by-interest/{interestId}")
    public List<UserInterestDTO> getByInterestId(@PathParam("interestId") BigInteger interestId) {
        return getMockUserInterests().stream().filter(ui -> ui.getId().getInterestId().equals(interestId)).toList();
    }

    @GET
    @Path("/{userId}/{interestId}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserInterestDTO getUserInterest(@PathParam("userId") BigInteger userId, @PathParam("interestId") BigInteger interestId) {
        return getMockUserInterests().stream()
            .filter(ui -> ui.getId().getUserId().equals(userId) && ui.getId().getInterestId().equals(interestId))
            .findFirst()
            .orElse(null);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserInterestDTO createUserInterest(UserInterestDTO userInterest) {
        userInterest.setId(new UserInterestIdDTO(BigInteger.valueOf(999), BigInteger.valueOf(999)));
        return userInterest;
    }

    @PUT
    @Path("/{userId}/{interestId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserInterestDTO updateUserInterest(@PathParam("userId") BigInteger userId, @PathParam("interestId") BigInteger interestId, UserInterestDTO userInterest) {
        userInterest.setId(new UserInterestIdDTO(userId, interestId));
        return userInterest;
    }

    @DELETE
    @Path("/{userId}/{interestId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteUserInterest(@PathParam("userId") BigInteger userId, @PathParam("interestId") BigInteger interestId) {
        return "UserInterest eliminato (mock): userId=" + userId + ", interestId=" + interestId;
    }
}
