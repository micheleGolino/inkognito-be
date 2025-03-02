package org.it.ms.inkognito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.it.ms.inkognito.entities.Interest;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

@QuarkusTest
@Transactional
public class InterestServiceTest {

	@Inject
	InterestService interestService;

	@Test
	public void testGetInterestNotFound() {
		Response response = interestService.getInterest(999L);
		assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void testCreateInterest() {
		Interest interest = new Interest();
		interest.setName("Hiking");
		interest.setDescription("Outdoor activities");

		Response response = interestService.createInterest(interest);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		Interest created = (Interest) response.getEntity();
		assertNotNull(created.getId());
	}
}
