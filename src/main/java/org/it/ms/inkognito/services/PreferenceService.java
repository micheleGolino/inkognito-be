package org.it.ms.inkognito.services;

import org.it.ms.inkognito.entities.Preference;
import org.it.ms.inkognito.dto.PreferenceDTO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PreferenceService {

    public Response getPreference(Long id) {
        Preference preference = Preference.findById(id);
        if (preference == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(preference).build();
    }

    @Transactional
    public Response createPreference(Preference preference) {
        if (preference != null) {
            Preference existing = Preference.findById(preference.getId());
            if (existing == null) {
                preference.persist();
                return Response.ok(preference).build();
            }
            return Response.status(Response.Status.CONFLICT).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @Transactional
    public Response updatePreference(Long id, Preference updatedPreference) {
        Preference preference = Preference.findById(id);
        if (preference == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        preference.setUserId(updatedPreference.getUserId());
        preference.setDesiredGender(updatedPreference.getDesiredGender());
        preference.setMaxDistance(updatedPreference.getMaxDistance());
        preference.setMinAge(updatedPreference.getMinAge());
        preference.setMaxAge(updatedPreference.getMaxAge());
        preference.setNote(updatedPreference.getNote());
        preference.persist();
        return Response.ok(preference).build();
    }

    @Transactional
    public Response deletePreference(Long id) {
        Preference preference = Preference.findById(id);
        if (preference == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        preference.delete();
        return Response.ok("Preference " + id + " deleted.").build();
    }

    public List<PreferenceDTO> findByUserId(BigInteger userId) {
        return Preference.list("Preference.findByUserId", userId)
                .stream()
                .map(p -> toDTO((Preference) p))
                .collect(Collectors.toList());
    }

    private PreferenceDTO toDTO(Preference p) {
        return new PreferenceDTO(
            p.getId(),
            p.getUserId(),
            p.getDesiredGender(),
            p.getMaxDistance(),
            p.getMinAge(),
            p.getMaxAge(),
            p.getNote(),
            p.getDateInsert()
        );
    }
}
