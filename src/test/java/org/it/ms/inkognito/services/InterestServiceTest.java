package org.it.ms.inkognito.services;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import javax.inject.Inject;
import java.util.List;
import org.it.ms.inkognito.entities.Interest;
import jakarta.ws.rs.core.Response;

@QuarkusTest
class InterestServiceTest {

    @Inject
    InterestService interestService;

    @Test
    void testGetInterestNotFound() {
        var response = interestService.getInterest(999L);
        Assertions.assertEquals(404, response.getStatus());
    }

    @Test
    void testCreateInterest() {
        Interest interest = new Interest();
        interest.setName("Hiking");
        interest.setDescription("Outdoor activities");

        Response response = interestService.createInterest(interest);
        Assertions.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Interest created = (Interest) response.getEntity();
        Assertions.assertNotNull(created.getId());
    }

    @Test
    void testFindByName() {
        List<?> result = interestService.findByName("Sport");
        Assertions.assertNotNull(result);
    }

    @Test
    void testFindAllOrdered() {
        List<?> result = interestService.findAllOrdered();
        Assertions.assertNotNull(result);
    }
}
