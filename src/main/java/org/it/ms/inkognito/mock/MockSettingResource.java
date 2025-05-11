package org.it.ms.inkognito.mock;

import org.it.ms.inkognito.dto.SettingDTO;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.DELETE;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Risorsa REST mock che restituisce impostazioni fittizie.
 * Endpoint: /mock/settings
 */
@Path("/mock/settings")
public class MockSettingResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SettingDTO> getMockSettings() {
        return Arrays.asList(
                new SettingDTO(BigInteger.valueOf(1), BigInteger.valueOf(1), "it", "dark", true, "", LocalDateTime.now().minusDays(15)),
                new SettingDTO(BigInteger.valueOf(2), BigInteger.valueOf(2), "en", "light", false, "", LocalDateTime.now().minusDays(10))
        );
    }

    @GET
    @Path("/by-user/{userId}")
    public List<SettingDTO> getByUserId(@PathParam("userId") BigInteger userId) {
        return getMockSettings().stream().filter(s -> s.getUserId().equals(userId)).toList();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public SettingDTO getSettingById(@PathParam("id") BigInteger id) {
        return getMockSettings().stream()
            .filter(s -> s.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SettingDTO createSetting(SettingDTO setting) {
        setting.setId(BigInteger.valueOf(999));
        return setting;
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SettingDTO updateSetting(@PathParam("id") BigInteger id, SettingDTO setting) {
        setting.setId(id);
        return setting;
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteSetting(@PathParam("id") BigInteger id) {
        return "Setting eliminato (mock): " + id;
    }
}
