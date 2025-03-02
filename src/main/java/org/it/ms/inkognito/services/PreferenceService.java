package org.it.ms.inkognito.services;

import org.it.ms.inkognito.entities.Preference;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

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
}
