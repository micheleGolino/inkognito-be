package org.it.ms.inkognito.resources;

import java.util.List;
import java.math.BigInteger;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.it.ms.inkognito.entities.Preference;
import org.it.ms.inkognito.services.PreferenceService;
import org.it.ms.inkognito.dto.PreferenceDTO;

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

@Path("/preferences")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PreferenceResource {

	@Inject
	PreferenceService preferenceService;

	@GET
	@Operation(summary = "List all preferences", description = "Retrieve all user preferences.")
	@APIResponse(responseCode = "200", description = "Preferences found")
	public List<Preference> listPreferences() {
		return Preference.listAll(Sort.by("id"));
	}

	@GET
	@Path("/{id}")
	@Operation(summary = "Get preference by ID", description = "Retrieve preference details by ID.")
	@APIResponse(responseCode = "200", description = "Preference found")
	@APIResponse(responseCode = "404", description = "Preference not found")
	public Response getPreference(@PathParam("id") Long id) {
		return preferenceService.getPreference(id);
	}

	@POST
	@Operation(summary = "Create a new preference", description = "Create a new user preference.")
	@APIResponse(responseCode = "201", description = "Preference created")
	@APIResponse(responseCode = "409", description = "Preference already exists")
	public Response createPreference(Preference preference) {
		return preferenceService.createPreference(preference);
	}

	@PUT
	@Path("/{id}")
	@Operation(summary = "Update preference", description = "Update an existing user preference.")
	@APIResponse(responseCode = "200", description = "Preference updated")
	@APIResponse(responseCode = "404", description = "Preference not found")
	public Response updatePreference(@PathParam("id") Long id, Preference updatedPreference) {
		return preferenceService.updatePreference(id, updatedPreference);
	}

	@DELETE
	@Path("/{id}")
	@Operation(summary = "Delete preference", description = "Delete a preference by ID.")
	@APIResponse(responseCode = "200", description = "Preference deleted")
	@APIResponse(responseCode = "404", description = "Preference not found")
	public Response deletePreference(@PathParam("id") Long id) {
		return preferenceService.deletePreference(id);
	}

	@GET
	@Path("/by-user/{userId}")
	public List<PreferenceDTO> getByUserId(@PathParam("userId") BigInteger userId) {
		return preferenceService.findByUserId(userId);
	}
}
