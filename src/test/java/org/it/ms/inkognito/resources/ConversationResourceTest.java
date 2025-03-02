package org.it.ms.inkognito.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

import java.math.BigInteger;

import org.it.ms.inkognito.entities.Conversation;
import org.it.ms.inkognito.services.ConversationService;
import org.junit.jupiter.api.Test;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.Response;

@QuarkusTest
public class ConversationResourceTest {

	@InjectMock
	ConversationService conversationService;

	@Test
	public void testGetConversationFound() {
		Conversation conversation = new Conversation();
		conversation.setId(BigInteger.valueOf(1));
		conversation.setUser1Id(BigInteger.valueOf(1));
		conversation.setUser2Id(BigInteger.valueOf(2));
		conversation.setNote("Chat between users");

		when(conversationService.getConversation(1L)).thenReturn(Response.ok(conversation).build());

		given().when().get("/conversations/1").then().statusCode(200).body("note", is("Chat between users"));
	}

	@Test
	public void testGetConversationNotFound() {
		when(conversationService.getConversation(2L)).thenReturn(Response.status(Response.Status.NOT_FOUND).build());

		given().when().get("/conversations/2").then().statusCode(404);
	}
}
