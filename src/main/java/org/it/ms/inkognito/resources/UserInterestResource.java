package org.it.ms.inkognito.resources;

import java.math.BigInteger;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.it.ms.inkognito.entities.UserInterest;
import org.it.ms.inkognito.services.UserInterestService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/user-interests")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserInterestResource {

	@Inject
	UserInterestService userInterestService;

	@GET
	@Path("/{userId}/{interestId}")
	@Operation(summary = "Get user interest", description = "Retrieve a user interest by user ID and interest ID.")
	@APIResponse(responseCode = "200", description = "User interest found")
	@APIResponse(responseCode = "404", description = "User interest not found")
	public Response getUserInterest(@PathParam("userId") BigInteger userId,
			@PathParam("interestId") BigInteger interestId) {
		return userInterestService.getUserInterest(userId, interestId);
	}

	@POST
	@Operation(summary = "Create a new user interest", description = "Create a new association between a user and an interest.")
	@APIResponse(responseCode = "201", description = "User interest created")
	@APIResponse(responseCode = "409", description = "User interest already exists")
	public Response createUserInterest(UserInterest userInterest) {
		return userInterestService.createUserInterest(userInterest);
	}

	@PUT
	@Path("/{userId}/{interestId}")
	@Operation(summary = "Update user interest", description = "Update an existing user interest.")
	@APIResponse(responseCode = "200", description = "User interest updated")
	@APIResponse(responseCode = "404", description = "User interest not found")
	public Response updateUserInterest(@PathParam("userId") BigInteger userId,
			@PathParam("interestId") BigInteger interestId, UserInterest updatedUserInterest) {
		return userInterestService.updateUserInterest(userId, interestId, updatedUserInterest);
	}

	@DELETE
	@Path("/{userId}/{interestId}")
	@Operation(summary = "Delete user interest", description = "Delete a user interest by user ID and interest ID.")
	@APIResponse(responseCode = "200", description = "User interest deleted")
	@APIResponse(responseCode = "404", description = "User interest not found")
	public Response deleteUserInterest(@PathParam("userId") BigInteger userId,
			@PathParam("interestId") BigInteger interestId) {
		return userInterestService.deleteUserInterest(userId, interestId);
	}
}
