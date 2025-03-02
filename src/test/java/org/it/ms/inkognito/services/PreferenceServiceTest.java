package org.it.ms.inkognito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigInteger;

import org.it.ms.inkognito.entities.Preference;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

@QuarkusTest
@Transactional
public class PreferenceServiceTest {

	@Inject
	PreferenceService preferenceService;

	@Test
	public void testGetPreferenceNotFound() {
		Response response = preferenceService.getPreference(999L);
		assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void testCreatePreference() {
		Preference pref = new Preference();
		pref.setUserId(BigInteger.valueOf(1));
		pref.setDesiredGender("Female");
		pref.setMaxDistance(50);
		pref.setMinAge(18);
		pref.setMaxAge(35);

		Response response = preferenceService.createPreference(pref);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		Preference created = (Preference) response.getEntity();
		assertNotNull(created.getId());
	}
}
