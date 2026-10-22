package api.test;

import java.util.List;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.PetEndpoints;
import api.payload.Pet;
import io.restassured.response.Response;

public class PetTest {

	Pet petPayload;
	Faker faker;
	int petId;

	@BeforeClass
	public void setupData() {
		petPayload = new Pet();
		faker = new Faker();

		petPayload.setId(faker.number().numberBetween(1000L, 9999999L));
		petPayload.setCategory(new Pet.Category(faker.number().numberBetween(1, 100), faker.animal().name()));
		petPayload.setName(faker.name().firstName());
		petPayload.setPhotoUrls(List.of(faker.internet().url()));
		petPayload.setTags(List.of(new Pet.Tag(faker.number().numberBetween(1, 100), faker.name().name())));
		// status is already "available" by default in Pet
	}

	@Test(priority = 1)
	public void testAddPet() {
		System.out.println("==========Inside Add pet Request===========");

		Response response = PetEndpoints.addPet(petPayload);
		response.then().log().body();

		petId = response.jsonPath().get("id");
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 2)
	public void testGetPetByStatus() {
		System.out.println("==========Inside GetPetByStatus Request===========");

		Response response = PetEndpoints.getByStatus(this.petPayload.getStatus());
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 3)
	public void testGetPetById() {
		System.out.println("==========Inside Get pet by Id Request===========");

		Response response = PetEndpoints.getById(this.petPayload.getId());
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 4)
	public void testupdatePet() {
		System.out.println("==========Inside update pet by Id Request===========");

		Response response = PetEndpoints.updatePet(petPayload);
		response.then().log().all();

		System.out.println("Id retrived by post request->" + petId);

		// int updatedId=response.jsonPath().get("id");
		JSONObject js = new JSONObject(response.asString());
		int updatedId = (int) js.get("id");
		System.out.println("updatedId->" + updatedId);

		Assert.assertEquals(updatedId, petId);
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 5)
	public void testDeletePet() {
		System.out.println("==========Inside Delete pet by Id Request===========");
		Response response = PetEndpoints.deletePet(this.petPayload.getId());
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
