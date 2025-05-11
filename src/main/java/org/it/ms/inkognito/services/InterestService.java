package org.it.ms.inkognito.services;

import org.it.ms.inkognito.entities.Interest;
import org.it.ms.inkognito.dto.InterestDTO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class InterestService {

	public Response getInterest(Long id) {
		Interest interest = Interest.findById(id);
		if (interest == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(interest).build();
	}
	
	@Transactional
	public Response createInterest(Interest interest) {
		if (interest != null) {
			Interest existing = Interest.findById(interest.getId());
			if (existing == null) {
				interest.persist();
				return Response.ok(interest).build();
			}
			return Response.status(Response.Status.CONFLICT).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}

	@Transactional
	public Response updateInterest(Long id, Interest updatedInterest) {
		Interest interest = Interest.findById(id);
		if (interest == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		interest.setName(updatedInterest.getName());
		interest.setDescription(updatedInterest.getDescription());
		interest.setNote(updatedInterest.getNote());
		interest.persist();
		return Response.ok(interest).build();
	}

	@Transactional
	public Response deleteInterest(Long id) {
		Interest interest = Interest.findById(id);
		if (interest == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		interest.delete();
		return Response.ok("Interest " + id + " deleted.").build();
	}

	public List<InterestDTO> findByName(String name) {
		return Interest.list("Interest.findByName", name)
				.stream()
				.map(i -> toDTO((Interest) i))
				.collect(Collectors.toList());
	}

	public List<InterestDTO> findAllOrdered() {
		return Interest.list("Interest.findAllOrdered")
				.stream()
				.map(i -> toDTO((Interest) i))
				.collect(Collectors.toList());
	}

	private InterestDTO toDTO(Interest i) {
		return new InterestDTO(
			i.getId(),
			i.getName(),
			i.getDescription(),
			i.getNote(),
			i.getDateInsert()
		);
	}
}
