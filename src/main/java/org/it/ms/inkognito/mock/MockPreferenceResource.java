package org.it.ms.inkognito.mock;

import org.it.ms.inkognito.dto.PreferenceDTO;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Consumes;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Risorsa REST mock che restituisce preferenze fittizie.
 * Endpoint: /mock/preferences
 */
@Path("/mock/preferences")
public class MockPreferenceResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PreferenceDTO> getMockPreferences() {
        return Arrays.asList(
                new PreferenceDTO(BigInteger.valueOf(1), BigInteger.valueOf(1), "Female", 50, 18, 35, "", LocalDateTime.now().minusDays(30)),
                new PreferenceDTO(BigInteger.valueOf(2), BigInteger.valueOf(2), "Male", 100, 20, 40, "", LocalDateTime.now().minusDays(20))
        );
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PreferenceDTO getPreferenceById(@PathParam("id") BigInteger id) {
        return getMockPreferences().stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public PreferenceDTO createPreference(PreferenceDTO preference) {
        preference.setId(BigInteger.valueOf(999));
        return preference;
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public PreferenceDTO updatePreference(@PathParam("id") BigInteger id, PreferenceDTO preference) {
        preference.setId(id);
        return preference;
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deletePreference(@PathParam("id") BigInteger id) {
        return "Preference eliminata (mock): " + id;
    }
}
