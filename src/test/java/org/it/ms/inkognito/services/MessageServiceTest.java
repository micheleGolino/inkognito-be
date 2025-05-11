package org.it.ms.inkognito.services;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import javax.inject.Inject;
import java.util.List;

@QuarkusTest
class MessageServiceTest {

    @Inject
    MessageService messageService;

    @Test
    void testFindByConversationId() {
        List<?> result = messageService.findByConversationId(java.math.BigInteger.valueOf(1));
        Assertions.assertNotNull(result);
    }

    @Test
    void testFindBySenderId() {
        List<?> result = messageService.findBySenderId(java.math.BigInteger.valueOf(1));
        Assertions.assertNotNull(result);
    }
}