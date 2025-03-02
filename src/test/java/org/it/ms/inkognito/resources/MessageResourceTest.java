package org.it.ms.inkognito.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.time.LocalDateTime;

import org.it.ms.inkognito.entities.Message;
import org.it.ms.inkognito.services.MessageService;
import org.junit.jupiter.api.Test;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.Response;

@QuarkusTest
public class MessageResourceTest {

	@InjectMock
	MessageService messageService;

	@Test
	public void testGetMessageFound() {
		Message message = new Message();
		message.setId(BigInteger.valueOf(1));
		message.setConversationId(BigInteger.valueOf(1));
		message.setSenderId(BigInteger.valueOf(1));
		message.setMessageText("Hello!");
		message.setSentAt(LocalDateTime.now());

		when(messageService.getMessage(1L)).thenReturn(Response.ok(message).build());

		given().when().get("/messages/1").then().statusCode(200).body("messageText", is("Hello!"));
	}

	@Test
	public void testGetMessageNotFound() {
		when(messageService.getMessage(2L)).thenReturn(Response.status(Response.Status.NOT_FOUND).build());

		given().when().get("/messages/2").then().statusCode(404);
	}
}
