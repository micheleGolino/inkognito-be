package org.it.ms.inkognito.services;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import javax.inject.Inject;

@QuarkusTest
class UserServiceTest {

    @Inject
    UserService userService;

    @Test
    void testFindByEmail() {
        var result = userService.findByEmail("test@example.com");
        Assertions.assertNull(result); // O assertNotNull se hai dati di test
    }

    @Test
    void testFindByUsername() {
        var result = userService.findByUsername("testuser");
        Assertions.assertNull(result); // O assertNotNull se hai dati di test
    }
}
