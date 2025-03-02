package org.it.ms.inkognito.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

import java.math.BigInteger;

import org.it.ms.inkognito.entities.User;
import org.it.ms.inkognito.services.UserService;
import org.junit.jupiter.api.Test;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.Response;

@QuarkusTest
public class UserResourceTest {

	@InjectMock
	UserService userService;

	@Test
	public void testGetUserFound() {
		// Crea un utente dummy
		User dummy = new User();
		dummy.setId(BigInteger.valueOf(1));
		dummy.setEmail("test@example.com");
		dummy.setFirstName("Test");
		dummy.setLastName("User");

		// Simula il comportamento del service
		when(userService.getUser(1L)).thenReturn(Response.ok(dummy).build());

		given().when().get("/users/1").then().statusCode(200).body("email", is("test@example.com"));
	}

	@Test
	public void testGetUserNotFound() {
		when(userService.getUser(2L)).thenReturn(Response.status(Response.Status.NOT_FOUND).build());

		given().when().get("/users/2").then().statusCode(404);
	}

	// Puoi aggiungere test per POST, PUT, DELETE seguendo lo stesso schema.
}
