package org.it.ms.inkognito.resources;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.it.ms.inkognito.entities.Interest;
import org.it.ms.inkognito.services.InterestService;
import org.it.ms.inkognito.dto.InterestDTO;

import io.quarkus.panache.common.Sort;
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

@Path("/interests")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InterestResource {

	@Inject
	InterestService interestService;

	@GET
	@Operation(summary = "List all interests", description = "Retrieve all interests.")
	@APIResponse(responseCode = "200", description = "Interests found")
	public List<Interest> listInterests() {
		return Interest.listAll(Sort.by("id"));
	}

	@GET
	@Path("/{id}")
	@Operation(summary = "Get interest by ID", description = "Retrieve interest details by ID.")
	@APIResponse(responseCode = "200", description = "Interest found")
	@APIResponse(responseCode = "404", description = "Interest not found")
	public Response getInterest(@PathParam("id") Long id) {
		return interestService.getInterest(id);
	}

	@POST
	@Operation(summary = "Create a new interest", description = "Create a new interest.")
	@APIResponse(responseCode = "201", description = "Interest created")
	@APIResponse(responseCode = "409", description = "Interest already exists")
	public Response createInterest(Interest interest) {
		return interestService.createInterest(interest);
	}

	@PUT
	@Path("/{id}")
	@Operation(summary = "Update interest", description = "Update an existing interest.")
	@APIResponse(responseCode = "200", description = "Interest updated")
	@APIResponse(responseCode = "404", description = "Interest not found")
	public Response updateInterest(@PathParam("id") Long id, Interest updatedInterest) {
		return interestService.updateInterest(id, updatedInterest);
	}

	@DELETE
	@Path("/{id}")
	@Operation(summary = "Delete interest", description = "Delete an interest by ID.")
	@APIResponse(responseCode = "200", description = "Interest deleted")
	@APIResponse(responseCode = "404", description = "Interest not found")
	public Response deleteInterest(@PathParam("id") Long id) {
		return interestService.deleteInterest(id);
	}

	@GET
	@Path("/by-name/{name}")
	public List<InterestDTO> getByName(@PathParam("name") String name) {
		return interestService.findByName(name);
	}

	@GET
	@Path("/ordered")
	public List<InterestDTO> getAllOrdered() {
		return interestService.findAllOrdered();
	}
}
