package org.it.ms.inkognito.services;

import org.it.ms.inkognito.dto.UserDTO;
import org.it.ms.inkognito.entities.User;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class UserService {

    public Response getUser(Long id) {
        User user = User.find("id", java.math.BigInteger.valueOf(id)).firstResult();
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(user).build();
    }

    @Transactional
    public Response createUser(User user) {
        if (user != null) {
            User existing = User.find("id", user.getId()).firstResult();
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
        User user = User.find("id", java.math.BigInteger.valueOf(id)).firstResult();
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        user.setEmail(updatedUser.getEmail());
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setUsername(updatedUser.getUsername());
        user.setBio(updatedUser.getBio());
        user.setImageUrl(updatedUser.getImageUrl());
        user.setNote(updatedUser.getNote());
        user.persist();
        return Response.ok(user).build();
    }

    @Transactional
    public Response deleteUser(Long id) {
        User user = User.find("id", java.math.BigInteger.valueOf(id)).firstResult();
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        user.delete();
        return Response.ok("User " + id + " deleted.").build();
    }

    public UserDTO findByEmail(String email) {
        return User.find("User.findByEmail", email)
                .firstResultOptional()
                .map(u -> toDTO((User) u))
                .orElse(null);
    }

    public UserDTO findByUsername(String username) {
        return User.find("User.findByUsername", username)
                .firstResultOptional()
                .map(u -> toDTO((User) u))
                .orElse(null);
    }

    private UserDTO toDTO(User u) {
        if (u == null) return null;
        return new UserDTO(
            u.getId(),
            u.getEmail(),
            u.getFirstName(),
            u.getLastName(),
            u.getUsername(),
            u.getBio(),
            u.getImageUrl(),
            u.getNote(),
            u.getDateInsert()
        );
    }
}
