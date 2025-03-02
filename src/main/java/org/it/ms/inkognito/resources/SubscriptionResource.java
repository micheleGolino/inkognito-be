package org.it.ms.inkognito.resources;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.it.ms.inkognito.entities.Subscription;
import org.it.ms.inkognito.services.SubscriptionService;

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

@Path("/subscriptions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SubscriptionResource {

	@Inject
	SubscriptionService subscriptionService;

	@GET
	@Operation(summary = "List all subscriptions", description = "Retrieve all subscriptions.")
	@APIResponse(responseCode = "200", description = "Subscriptions found")
	public List<Subscription> listSubscriptions() {
		return Subscription.listAll(Sort.by("id"));
	}

	@GET
	@Path("/{id}")
	@Operation(summary = "Get subscription by ID", description = "Retrieve subscription details by ID.")
	@APIResponse(responseCode = "200", description = "Subscription found")
	@APIResponse(responseCode = "404", description = "Subscription not found")
	public Response getSubscription(@PathParam("id") Long id) {
		return subscriptionService.getSubscription(id);
	}

	@POST
	@Operation(summary = "Create a new subscription", description = "Create a new subscription.")
	@APIResponse(responseCode = "201", description = "Subscription created")
	@APIResponse(responseCode = "409", description = "Subscription already exists")
	public Response createSubscription(Subscription subscription) {
		return subscriptionService.createSubscription(subscription);
	}

	@PUT
	@Path("/{id}")
	@Operation(summary = "Update subscription", description = "Update an existing subscription.")
	@APIResponse(responseCode = "200", description = "Subscription updated")
	@APIResponse(responseCode = "404", description = "Subscription not found")
	public Response updateSubscription(@PathParam("id") Long id, Subscription updatedSubscription) {
		return subscriptionService.updateSubscription(id, updatedSubscription);
	}

	@DELETE
	@Path("/{id}")
	@Operation(summary = "Delete subscription", description = "Delete a subscription by ID.")
	@APIResponse(responseCode = "200", description = "Subscription deleted")
	@APIResponse(responseCode = "404", description = "Subscription not found")
	public Response deleteSubscription(@PathParam("id") Long id) {
		return subscriptionService.deleteSubscription(id);
	}
}
