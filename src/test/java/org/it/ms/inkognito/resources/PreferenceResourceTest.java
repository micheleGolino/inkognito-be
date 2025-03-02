package org.it.ms.inkognito.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

import java.math.BigInteger;

import org.it.ms.inkognito.entities.Preference;
import org.it.ms.inkognito.services.PreferenceService;
import org.junit.jupiter.api.Test;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.Response;

@QuarkusTest
public class PreferenceResourceTest {

	@InjectMock
	PreferenceService preferenceService;

	@Test
	public void testGetPreferenceFound() {
		Preference pref = new Preference();
		pref.setId(BigInteger.valueOf(1));
		pref.setUserId(BigInteger.valueOf(1));
		pref.setDesiredGender("Female");
		pref.setMaxDistance(50);
		pref.setMinAge(18);
		pref.setMaxAge(35);

		when(preferenceService.getPreference(1L)).thenReturn(Response.ok(pref).build());

		given().when().get("/preferences/1").then().statusCode(200).body("desiredGender", is("Female"));
	}

	@Test
	public void testGetPreferenceNotFound() {
		when(preferenceService.getPreference(2L)).thenReturn(Response.status(Response.Status.NOT_FOUND).build());

		given().when().get("/preferences/2").then().statusCode(404);
	}
}
