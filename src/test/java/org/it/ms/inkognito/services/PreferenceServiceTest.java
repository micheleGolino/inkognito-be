package org.it.ms.inkognito.services;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import javax.inject.Inject;
import java.math.BigInteger;
import jakarta.ws.rs.core.Response;
import org.it.ms.inkognito.entities.Preference;

@QuarkusTest
class PreferenceServiceTest {

    @Inject
    PreferenceService preferenceService;

    @Test
    void testGetPreferenceNotFound() {
        Response response = preferenceService.getPreference(999L);
        Assertions.assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    void testCreatePreference() {
        Preference pref = new Preference();
        pref.setUserId(BigInteger.valueOf(1));
        pref.setDesiredGender("Female");
        pref.setMaxDistance(50);
        pref.setMinAge(18);
        pref.setMaxAge(35);

        Response response = preferenceService.createPreference(pref);
        Assertions.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Preference created = (Preference) response.getEntity();
        Assertions.assertNotNull(created.getId());
    }

    @Test
    void testFindByUserId() {
        var result = preferenceService.findByUserId(BigInteger.valueOf(1));
        Assertions.assertNotNull(result);
    }
}
