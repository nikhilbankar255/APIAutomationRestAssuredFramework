package api.endpoints;

public class Routes {

	/*
	URI->   https://petstore.swagger.io/
	post->  v2/user
	Get->   v2/user/{username}
	Put->   v2/user/{username}
	Delete->  v2/ user/{username}
*/
	
	public static String base_url="https://petstore.swagger.io/v2";
	
	//User module
	public static String post_url=base_url+"/user";
	public static String get_url=base_url+"/user/{username}";
	public static String update_url=base_url+"/user/{username}";
	public static String delete_url=base_url+"/user/{username}";
	
	//store module
	
	//pet module
	
}
