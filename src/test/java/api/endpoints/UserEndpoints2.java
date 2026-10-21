package api.endpoints;

import java.util.ResourceBundle;
import static io.restassured.RestAssured.*;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints2 {
	//created for getting URL's from properties file
		static ResourceBundle getURL()
		{
			ResourceBundle routes=ResourceBundle.getBundle("routes"); //name of the properties file
			return routes;
		}
		
		public static Response createUser(User payload)
		{
			String post_url=getURL().getString("post_url");
			
			Response response=given()
				.contentType("application/json")
				.accept(ContentType.JSON)
				.body(payload)
			.when()
				.post(post_url);
			
			return response;
			
		}
		
		public static Response getUser(String userName)
		{
			String get_url=getURL().getString("get_url");
			Response response=given()
				.pathParam("username",userName)
			.when()
				.get(get_url);
			
			return response;
			
		}
		
		public static Response updateUser(User payload,String userName)
		{
			String update_url=getURL().getString("update_url");

			Response response=given()
								.contentType("application/json")
								.accept(ContentType.JSON)
								.body(payload)
								.pathParam("username", userName)
							.when()
								.put(update_url);
						return response;
		}
		
		public static Response deleteUser(String userName)
		{
			String delete_url=getURL().getString("delete_url");

			Response response=given()
				.pathParam("username", userName)
			.when()
				.delete(delete_url);
				return response;
		}
}
