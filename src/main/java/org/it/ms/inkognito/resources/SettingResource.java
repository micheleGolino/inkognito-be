package org.it.ms.inkognito.resources;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.it.ms.inkognito.entities.Setting;
import org.it.ms.inkognito.services.SettingService;

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

@Path("/settings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SettingResource {

	@Inject
	SettingService settingService;

	@GET
	@Operation(summary = "List all settings", description = "Retrieve all user settings.")
	@APIResponse(responseCode = "200", description = "Settings found")
	public List<Setting> listSettings() {
		return Setting.listAll(Sort.by("id"));
	}

	@GET
	@Path("/{id}")
	@Operation(summary = "Get setting by ID", description = "Retrieve setting details by ID.")
	@APIResponse(responseCode = "200", description = "Setting found")
	@APIResponse(responseCode = "404", description = "Setting not found")
	public Response getSetting(@PathParam("id") Long id) {
		return settingService.getSetting(id);
	}

	@POST
	@Operation(summary = "Create a new setting", description = "Create a new user setting.")
	@APIResponse(responseCode = "201", description = "Setting created")
	@APIResponse(responseCode = "409", description = "Setting already exists")
	public Response createSetting(Setting setting) {
		return settingService.createSetting(setting);
	}

	@PUT
	@Path("/{id}")
	@Operation(summary = "Update setting", description = "Update an existing user setting.")
	@APIResponse(responseCode = "200", description = "Setting updated")
	@APIResponse(responseCode = "404", description = "Setting not found")
	public Response updateSetting(@PathParam("id") Long id, Setting updatedSetting) {
		return settingService.updateSetting(id, updatedSetting);
	}

	@DELETE
	@Path("/{id}")
	@Operation(summary = "Delete setting", description = "Delete a setting by ID.")
	@APIResponse(responseCode = "200", description = "Setting deleted")
	@APIResponse(responseCode = "404", description = "Setting not found")
	public Response deleteSetting(@PathParam("id") Long id) {
		return settingService.deleteSetting(id);
	}
}
