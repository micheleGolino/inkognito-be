package org.it.ms.inkognito.resources;

import java.util.List;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.it.ms.inkognito.entities.User;
import org.it.ms.inkognito.services.UserService;
import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject 
    UserService usrService;
    
    @GET
    @Operation(summary = "List all users", description = "Retrieve all registered users.")
    @APIResponse(responseCode = "200", description = "Users found")
    public List<User> listUsers() {
        return User.listAll(Sort.by("id"));
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get user by ID", description = "Retrieve user details by ID.")
    @APIResponse(responseCode = "200", description = "User found")
    @APIResponse(responseCode = "404", description = "User not found")
    public Response getUser(@PathParam("id") Long id) {
        return usrService.getUser(id);
    }

    @POST
    @Operation(summary = "Create a new user", description = "Register a new user.")
    @APIResponse(responseCode = "201", description = "User created")
    @APIResponse(responseCode = "409", description = "User already exists")
    public Response createUser(User user) {
        return usrService.createUser(user);
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Update user", description = "Update an existing user.")
    @APIResponse(responseCode = "200", description = "User updated")
    @APIResponse(responseCode = "404", description = "User not found")
    public Response updateUser(@PathParam("id") Long id, User updatedUser) {
        return usrService.updateUser(id, updatedUser);
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete user", description = "Delete a user by ID.")
    @APIResponse(responseCode = "200", description = "User deleted")
    @APIResponse(responseCode = "404", description = "User not found")
    public Response deleteUser(@PathParam("id") Long id) {
        return usrService.deleteUser(id);
    }
}
