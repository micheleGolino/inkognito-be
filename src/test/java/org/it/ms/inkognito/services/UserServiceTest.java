package org.it.ms.inkognito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.it.ms.inkognito.entities.User;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

@QuarkusTest
@Transactional
public class UserServiceTest {

	@Inject
	UserService userService;

	@Test
	public void testGetUserNotFound() {
		Response response = userService.getUser(999L);
		assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void testCreateUser() {
		User user = new User();
		// Non impostare l'ID, viene generato automaticamente
		user.setEmail("newuser@example.com");
		user.setPassword("hashedpassword");
		user.setFirstName("New");
		user.setLastName("User");

		Response response = userService.createUser(user);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		User created = (User) response.getEntity();
		assertNotNull(created.getId());
	}
}
