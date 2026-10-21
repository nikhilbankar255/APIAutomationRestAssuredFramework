package api.test;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.Routes;
import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.module.jsv.JsonSchemaValidator;

public class SchemaTest {

	User userPayload;
	@Test
	public void jsonSchemaValidation()
	{	
		Faker faker=new Faker();
		userPayload= new User();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().phoneNumber());
		
		given()
		.when()
			.get(Routes.get_url+UserEndpoints.getUser(this.userPayload.getUsername())		
		.then()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getUserJsonSchema.json")));
	}
}
