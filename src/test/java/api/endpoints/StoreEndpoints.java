package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import api.payload.Store;


public class StoreEndpoints {

	public static Response createOrder(Store payload)
	{
		Response response=given()
				.contentType("application/json")
				.accept(ContentType.JSON)
				.body(payload)
				.when()
					.post(Routes.store_postUrl);
		
				return response;
	}
	
	public static Response getInventory()
	{
		Response response=given()
		.when()
			.get(Routes.store_getInventoryUrl);
		
		return response;
	}
	
	public static Response getOrder(int id)
	{
		Response response=given()
				.pathParam("orderId", id)
		.when()
			.get(Routes.store_getOrderUrl);
		
		return response;
		
	}
	
	public static Response deleteOrder(int id)
	{
		Response response=given()
				.pathParam("orderId", id)
		.when()
			.delete(Routes.store_deleteUrl);
		return response;
	}
}
