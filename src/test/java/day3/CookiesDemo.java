package day3;
import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CookiesDemo {

	@Test(priority = 0)
	public void testCookies() {
		
		given()
		
		.when()
			.get("https://www.google.com/")		
		.then()
		//.cookie("AEC","Aaa9EJrQ6PCeK0lpO8C2sXTr1Kj5tj0L5WYBiOWRuW2Xi16wqhDEu_VAZA")
			.log().all();		
	}
	
	@Test(priority = 1)
	public void captureCookie() {
		
		//we are capturing the response which includes status code, cookies, headers information and response body
		Response response=given().when().get("https://www.google.com/");
		
		//Get Single Cookie info
		String cookie_value=response.getCookie("AEC");
		System.out.println("Value of AEC cookie"+cookie_value);
		
		//Get info of all the cookies		
		Map<String,String> cookies =response.getCookies();
		
		//to get info of all the keys
		System.out.println(cookies.keySet());
		
		//to get info of all the values
		System.out.println(cookies.values());
		
		//to print the values in key and value pair
		for(String cookie: cookies.keySet()) {
			System.out.println(cookie+" - "+response.then().cookie(cookie));
		}
		
		//or 		
		for(Map.Entry<String, String> values : cookies.entrySet()) {
			System.out.println(values.getKey()+" - "+values.getValue());
		}
		
	}
}
