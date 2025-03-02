package org.it.ms.inkognito.services;

import java.math.BigInteger;

import org.it.ms.inkognito.entities.UserInterest;
import org.it.ms.inkognito.entities.UserInterestId;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class UserInterestService {

	public Response getUserInterest(BigInteger userId, BigInteger interestId) {
		UserInterestId id = new UserInterestId(userId, interestId);
		UserInterest userInterest = UserInterest.findById(id);
		if (userInterest == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(userInterest).build();
	}

	@Transactional
	public Response createUserInterest(UserInterest userInterest) {
		if (userInterest != null) {
			UserInterest existing = UserInterest.findById(userInterest.getId());
			if (existing == null) {
				userInterest.persist();
				return Response.ok(userInterest).build();
			}
			return Response.status(Response.Status.CONFLICT).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}

	@Transactional
	public Response updateUserInterest(BigInteger userId, BigInteger interestId, UserInterest updatedUserInterest) {
		UserInterestId id = new UserInterestId(userId, interestId);
		UserInterest userInterest = UserInterest.findById(id);
		if (userInterest == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		userInterest.setNote(updatedUserInterest.getNote());
		userInterest.persist();
		return Response.ok(userInterest).build();
	}

	@Transactional
	public Response deleteUserInterest(BigInteger userId, BigInteger interestId) {
		UserInterestId id = new UserInterestId(userId, interestId);
		UserInterest userInterest = UserInterest.findById(id);
		if (userInterest == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		userInterest.delete();
		return Response.ok("UserInterest (" + userId + ", " + interestId + ") deleted.").build();
	}
}
