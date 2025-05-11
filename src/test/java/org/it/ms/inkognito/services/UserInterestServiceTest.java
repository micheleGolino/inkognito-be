package org.it.ms.inkognito.services;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import javax.inject.Inject;
import java.util.List;
import java.math.BigInteger;

@QuarkusTest
class UserInterestServiceTest {

    @Inject
    UserInterestService userInterestService;

    @Test
    void testFindByUserId() {
        List<?> result = userInterestService.findByUserId(BigInteger.valueOf(1));
        Assertions.assertNotNull(result);
    }

    @Test
    void testFindByInterestId() {
        List<?> result = userInterestService.findByInterestId(BigInteger.valueOf(1));
        Assertions.assertNotNull(result);
    }
}
