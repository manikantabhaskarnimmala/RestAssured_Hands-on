package day4;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ParsingJSONResponseData {
	Response response;
	SoftAssert softAssert = new SoftAssert();
	@Test
	public void dataParsingTest() {
		//Path of the JSON file and invoked using json-server of npm.
		//approach1
		given()
			.contentType("application/json")
		.when()
			.get("http://localhost:3000/store")
		.then().statusCode(200).header("Content-Type", "application/json")
		.body("books[1].title", equalTo("Harry Potter and the Philosopher's Stone"));
		
		
		//approach2		
		response=given().contentType("application/json").when().get("http://localhost:3000/store");
		int statusCode =response.getStatusCode();
		String contentTypeHeader =response.getHeader("Content-Type");
		softAssert.assertEquals(statusCode, 200);
		softAssert.assertEquals(contentTypeHeader,"application/json");
		
		String actualTitle = response.jsonPath().get("books[1].title").toString();
		//.toString() will be converting object into a String
		softAssert.assertEquals(actualTitle, "Harry Potter and the Philosopher's Stone");
		softAssert.assertAll();
				
	}
	
	@Test
	public void testResponseBody() {
		//If we want to validate the values even if the position of it changes in the response.
		//to traverse the JSON we need to use JSONObject class which is predefined class and we need to add the related dependency.
		
		response=given()
					.accept(ContentType.JSON)
				.when().get("http://localhost:3000/store");
		
		//JSONObject
		JSONObject jo = new JSONObject(response.asString()); 
		//Here we are converting response to JSONObject type using asString() and passed as a parameter into it.
		
		JSONArray books = jo.getJSONArray("books");

	    for (int i = 0; i < books.length(); i++) {
	        JSONObject book = books.getJSONObject(i);
	        String title = book.getString("title");
	        System.out.println(title);
	    }
		
		

		
	}
	
	

}





























