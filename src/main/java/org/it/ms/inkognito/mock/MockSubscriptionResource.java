package org.it.ms.inkognito.mock;

import org.it.ms.inkognito.dto.SubscriptionDTO;

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
 * Risorsa REST mock che restituisce sottoscrizioni fittizie.
 * Endpoint: /mock/subscriptions
 */
@Path("/mock/subscriptions")
public class MockSubscriptionResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SubscriptionDTO> getMockSubscriptions() {
        return Arrays.asList(
                new SubscriptionDTO(BigInteger.valueOf(1), BigInteger.valueOf(1), "Premium", LocalDateTime.now().minusMonths(1), LocalDateTime.now().plusMonths(1), "", LocalDateTime.now().minusMonths(1)),
                new SubscriptionDTO(BigInteger.valueOf(2), BigInteger.valueOf(2), "Free", LocalDateTime.now().minusMonths(2), LocalDateTime.now().plusMonths(2), "", LocalDateTime.now().minusMonths(2))
        );
    }

    @GET
    @Path("/by-user/{userId}")
    public List<SubscriptionDTO> getByUserId(@PathParam("userId") BigInteger userId) {
        return getMockSubscriptions().stream().filter(s -> s.getUserId().equals(userId)).toList();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public SubscriptionDTO getSubscriptionById(@PathParam("id") BigInteger id) {
        return getMockSubscriptions().stream()
            .filter(s -> s.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SubscriptionDTO createSubscription(SubscriptionDTO subscription) {
        subscription.setId(BigInteger.valueOf(999));
        return subscription;
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SubscriptionDTO updateSubscription(@PathParam("id") BigInteger id, SubscriptionDTO subscription) {
        subscription.setId(id);
        return subscription;
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteSubscription(@PathParam("id") BigInteger id) {
        return "Subscription eliminata (mock): " + id;
    }
}
