package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.StoreEndpoints;
import api.payload.Store;
import io.restassured.response.Response;

public class StoreTest {

	Faker faker;
	Store storePayload;

	@BeforeClass
	public void setupData() {
		faker = new Faker();
		storePayload = new Store();

		storePayload.setId(faker.idNumber().hashCode());
		storePayload.setPetId(faker.idNumber().hashCode());
		storePayload.setQuantity(faker.number().hashCode());

	}

	@Test(priority = 1)
	public void testGetInventory() {
		System.out.println("==========Inside Get Inventory Request===========");

		Response response = StoreEndpoints.getInventory();
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 2)
	public void testCreateOrder() {
		System.out.println("==========Inside Post Request===========");

		Response response = StoreEndpoints.createOrder(storePayload);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 3)
	public void testGetOrder() {
		System.out.println("==========Inside get order Request===========");

		Response response = StoreEndpoints.getOrder(this.storePayload.getId());
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 4)
	public void testDeleteOrder() {
		System.out.println("==========Inside Delete order Request===========");

		Response response = StoreEndpoints.deleteOrder(this.storePayload.getId());
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
