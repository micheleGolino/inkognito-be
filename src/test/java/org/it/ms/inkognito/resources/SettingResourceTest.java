package org.it.ms.inkognito.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

import java.math.BigInteger;

import org.it.ms.inkognito.entities.Setting;
import org.it.ms.inkognito.services.SettingService;
import org.junit.jupiter.api.Test;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.Response;

@QuarkusTest
public class SettingResourceTest {

	@InjectMock
	SettingService settingService;

	@Test
	public void testGetSettingFound() {
		Setting setting = new Setting();
		setting.setId(BigInteger.valueOf(1));
		setting.setUserId(BigInteger.valueOf(1));
		setting.setLanguage("en");
		setting.setTheme("dark");

		when(settingService.getSetting(1L)).thenReturn(Response.ok(setting).build());

		given().when().get("/settings/1").then().statusCode(200).body("language", is("en")).body("theme", is("dark"));
	}

	@Test
	public void testGetSettingNotFound() {
		when(settingService.getSetting(2L)).thenReturn(Response.status(Response.Status.NOT_FOUND).build());

		given().when().get("/settings/2").then().statusCode(404);
	}
}
