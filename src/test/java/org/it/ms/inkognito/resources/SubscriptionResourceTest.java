package org.it.ms.inkognito.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.time.LocalDateTime;

import org.it.ms.inkognito.entities.Subscription;
import org.it.ms.inkognito.services.SubscriptionService;
import org.junit.jupiter.api.Test;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.Response;

@QuarkusTest
public class SubscriptionResourceTest {

	@InjectMock
	SubscriptionService subscriptionService;

	@Test
	public void testGetSubscriptionFound() {
		Subscription subscription = new Subscription();
		subscription.setId(BigInteger.valueOf(1));
		subscription.setUserId(BigInteger.valueOf(1));
		subscription.setSubscriptionType("Premium");
		subscription.setStartDate(LocalDateTime.now());
		subscription.setEndDate(LocalDateTime.now().plusDays(30));

		when(subscriptionService.getSubscription(1L)).thenReturn(Response.ok(subscription).build());

		given().when().get("/subscriptions/1").then().statusCode(200).body("subscriptionType", is("Premium"));
	}

	@Test
	public void testGetSubscriptionNotFound() {
		when(subscriptionService.getSubscription(2L)).thenReturn(Response.status(Response.Status.NOT_FOUND).build());

		given().when().get("/subscriptions/2").then().statusCode(404);
	}
}
