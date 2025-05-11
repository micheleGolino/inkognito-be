package org.it.ms.inkognito.services;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import javax.inject.Inject;
import java.math.BigInteger;
import java.util.List;

@QuarkusTest
class ConversationServiceTest {

    @Inject
    ConversationService conversationService;

    @Test
    void testFindByUser1Id() {
        List<?> result = conversationService.findByUser1Id(BigInteger.valueOf(1));
        Assertions.assertNotNull(result);
    }

    @Test
    void testFindByUser2Id() {
        List<?> result = conversationService.findByUser2Id(BigInteger.valueOf(2));
        Assertions.assertNotNull(result);
    }
}
