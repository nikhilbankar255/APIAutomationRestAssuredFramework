package api.endpoints;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import api.payload.Pet;

public class PetEndpoints {

	
	public static Response getByStatus(String status)
	{
		Response response=given()
				.queryParam("status", status)
		.when()
			.get(Routes.pet_getByStatusUrl);
		return response;
	}
	
	public static Response addPet(Pet payload)
	{
		Response response=given()
				.contentType("application/json")
				.body(payload)
		.when()
			.post(Routes.pet_postUrl);
		return response;
		
	}
	
	public static Response getById(long id )
	{
		Response response= given()
				.pathParam("petId", id)
				.when()
					.get(Routes.pet_getByIdUrl);
					return response;
	}
	
	public static Response updatePet(Pet payload)
	{
		
		Response response=given()
				.contentType("application/json")
				.body(payload)
		.when()
			.put(Routes.pet_updateUrl);
		return response;
	}
	 
	public static Response deletePet(long id)
	{
		Response response=given()
			.pathParam("petId", id)
		.when()
			.delete(Routes.pet_deleteUrl);
		return response;
	}
}
