package api.test;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {

	Faker faker;
	User userPayload;
	public Logger logger;
	@BeforeClass
	public void setupData()
	{
		faker= new Faker();
		userPayload=new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().phoneNumber());
		
		//logs
		logger=LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority = 1)
	public void testPostUser()
	{
		logger.info("************ Creating user ***********");
		System.out.println("==========Inside Post Request===========");
		Response response=UserEndpoints.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("************ user createrd ***********");

	}
	
	@Test(priority = 2)
	public void testGetUserByName()
	{		System.out.println("==========Inside Get Request===========");
	logger.info("************ Reading user Info ***********");

		System.out.println("name is->"+this.userPayload.getUsername());
		Response response=UserEndpoints.getUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("************ User info is displayed ***********");

	}
	
	@Test(priority = 3)
	public void testUpdateUser()
	{
		System.out.println("==========Inside update Request===========");
		logger.info("************ Updating user ***********");

		System.out.println("name is->"+this.userPayload.getUsername());

		//update below data
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndpoints.updateUser(userPayload,this.userPayload.getUsername());
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("************ user updated ***********");

		//checking data after update
		System.out.println("After update name is->"+this.userPayload.getUsername());
		Response responseAfterupdate=UserEndpoints.getUser(this.userPayload.getUsername());
		responseAfterupdate.then().log().all();
		Assert.assertEquals(responseAfterupdate.getStatusCode(), 200);
	}
	
	@Test(priority = 4)
	public void testDeleteUser()
	{
		System.out.println("==========Inside Delete Request===========");
		logger.info("************ Deleting user ***********");

		System.out.println("name is->"+this.userPayload.getUsername());

		Response response=UserEndpoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("************ user deleted ***********");
	}
}
