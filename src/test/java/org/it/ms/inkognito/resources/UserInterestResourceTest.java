package org.it.ms.inkognito.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

import java.math.BigInteger;

import org.it.ms.inkognito.entities.UserInterest;
import org.it.ms.inkognito.entities.UserInterestId;
import org.it.ms.inkognito.services.UserInterestService;
import org.junit.jupiter.api.Test;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.Response;

@QuarkusTest
public class UserInterestResourceTest {

	@InjectMock
	UserInterestService userInterestService;

	@Test
	public void testGetUserInterestFound() {
		UserInterest userInterest = new UserInterest();
		UserInterestId id = new UserInterestId(BigInteger.valueOf(1), BigInteger.valueOf(1));
		userInterest.setId(id);
		userInterest.setNote("Test note");

		when(userInterestService.getUserInterest(BigInteger.valueOf(1), BigInteger.valueOf(1)))
				.thenReturn(Response.ok(userInterest).build());

		given().when().get("/user-interests/1/1").then().statusCode(200).body("note", is("Test note"));
	}

	@Test
	public void testGetUserInterestNotFound() {
		when(userInterestService.getUserInterest(BigInteger.valueOf(2), BigInteger.valueOf(2)))
				.thenReturn(Response.status(Response.Status.NOT_FOUND).build());

		given().when().get("/user-interests/2/2").then().statusCode(404);
	}
}
