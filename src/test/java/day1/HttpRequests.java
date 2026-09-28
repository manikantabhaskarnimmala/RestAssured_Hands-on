package day1;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

/*
 * given(): Whenever we are sending the api request we need some prerequisites like content type, set cookies, add auth, add parameters, set headers info etc.
when(): what type of requests are sending like get, post, put, create
then(): Here we need to add validation code like response code, response body, headers and cookies.

 */

public class HttpRequests {

	int id;
	@Test(priority = 0)
	public void getUser() {
		given().when().get("https://reqres.in/api/users/2") // trigger the api
				.then().statusCode(200) // validate the status code
				.body("data.id", equalTo(2)) // asserting id is equal to 2
				.log().all(); // printing the api response with headers and response
	}

	@Test(priority = 1)
	public void getUsers() {
		given()
		.when().get("https://reqres.in/api/users?page=2")
		.then().statusCode(200).log().all();
	}

	@Test(priority = 2)
	public void createUser() {

		Map<String, String> data = new HashMap<>();
		data.put("name", "morpheus");
		data.put("job", "leader");

		id=given()
		.contentType("application/json").body(data) // for post user we need to pass some information like
															// contentType and we need to pass data as well
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
		//.then().statusCode(201).log().all();
		System.out.println(id);

	}
	
	@Test(priority = 3, dependsOnMethods = "createUser")
	public void updateUser() {
		
		Map<String, String> data = new HashMap<>();
		data.put("name", "morpheus");
		data.put("job", "zion resident");
		
		given()
		.contentType("application/json").body(data)		
		.when()
			.put("https://reqres.in/api/users/"+id)		
		.then().statusCode(200).log().all();
		
	}
	
	@Test(priority=4, dependsOnMethods = "updateUser")
	public void deleteUser() {
		
		given()
		
		.when()
			.delete("https://reqres.in/api/users/2")
		.then()
			.statusCode(204).log().all();
	}

}
