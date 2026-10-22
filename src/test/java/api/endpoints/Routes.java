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
	public static String store_getInventoryUrl= base_url+"/store/inventory";
	public static String store_postUrl=base_url+"/store/order";
	public static String store_getOrderUrl=base_url+"/store/order/{orderId}";
	public static String store_deleteUrl=base_url+"/store/order/{orderId}";
		
	//pet module
	
}
