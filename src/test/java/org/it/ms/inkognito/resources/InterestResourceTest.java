package org.it.ms.inkognito.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

import java.math.BigInteger;

import org.it.ms.inkognito.entities.Interest;
import org.it.ms.inkognito.services.InterestService;
import org.junit.jupiter.api.Test;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.Response;

@QuarkusTest
public class InterestResourceTest {

	@InjectMock
	InterestService interestService;

	@Test
	public void testGetInterestFound() {
		Interest interest = new Interest();
		interest.setId(BigInteger.valueOf(1));
		interest.setName("Hiking");
		interest.setDescription("Outdoor activities");

		when(interestService.getInterest(1L)).thenReturn(Response.ok(interest).build());

		given().when().get("/interests/1").then().statusCode(200).body("name", is("Hiking"));
	}

	@Test
	public void testGetInterestNotFound() {
		when(interestService.getInterest(2L)).thenReturn(Response.status(Response.Status.NOT_FOUND).build());

		given().when().get("/interests/2").then().statusCode(404);
	}
}
