package org.it.ms.inkognito.services;

import org.it.ms.inkognito.entities.Subscription;
import org.it.ms.inkognito.dto.SubscriptionDTO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class SubscriptionService {

	public Response getSubscription(Long id) {
		Subscription subscription = Subscription.findById(id);
		if (subscription == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(subscription).build();
	}

	@Transactional
	public Response createSubscription(Subscription subscription) {
		if (subscription != null) {
			Subscription existing = Subscription.findById(subscription.getId());
			if (existing == null) {
				subscription.persist();
				return Response.ok(subscription).build();
			}
			return Response.status(Response.Status.CONFLICT).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}

	@Transactional
	public Response updateSubscription(Long id, Subscription updatedSubscription) {
		Subscription subscription = Subscription.findById(id);
		if (subscription == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		subscription.setUserId(updatedSubscription.getUserId());
		subscription.setSubscriptionType(updatedSubscription.getSubscriptionType());
		subscription.setStartDate(updatedSubscription.getStartDate());
		subscription.setEndDate(updatedSubscription.getEndDate());
		subscription.setNote(updatedSubscription.getNote());
		subscription.persist();
		return Response.ok(subscription).build();
	}

	@Transactional
	public Response deleteSubscription(Long id) {
		Subscription subscription = Subscription.findById(id);
		if (subscription == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		subscription.delete();
		return Response.ok("Subscription " + id + " deleted.").build();
	}

	public List<SubscriptionDTO> findByUserId(BigInteger userId) {
		return Subscription.list("Subscription.findByUserId", userId)
				.stream()
				.map(s -> toDTO((Subscription) s))
				.collect(Collectors.toList());
	}

	private SubscriptionDTO toDTO(Subscription s) {
		return new SubscriptionDTO(
			s.getId(),
			s.getUserId(),
			s.getSubscriptionType(),
			s.getStartDate(),
			s.getEndDate(),
			s.getNote(),
			s.getDateInsert()
		);
	}
}
