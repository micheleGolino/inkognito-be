package org.it.ms.inkognito.services;

import org.it.ms.inkognito.entities.User;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class UserService {

    public Response getUser(Long id) {
        User user = User.findById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(user).build();
    }

    @Transactional
    public Response createUser(User user) {
        if (user != null) {
            User existing = User.findById(user.getId());
            if (existing == null) {
                user.persist();
                return Response.ok(user).build();
            } else {
                return Response.status(Response.Status.CONFLICT).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @Transactional
    public Response updateUser(Long id, User updatedUser) {
        User user = User.findById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        user.setEmail(updatedUser.getEmail());
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setNote(updatedUser.getNote());
        user.persist();
        return Response.ok(user).build();
    }

    @Transactional
    public Response deleteUser(Long id) {
        User user = User.findById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        user.delete();
        return Response.ok("User " + id + " deleted.").build();
    }
}
