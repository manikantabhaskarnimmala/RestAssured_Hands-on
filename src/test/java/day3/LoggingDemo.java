package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

public class LoggingDemo {

	@Test
	public void testLogs() {

		System.out.println("To log everything from the response");
		given()
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.log().all();
		
		System.out.println("To log only body from response");
		given()
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.log().body();
		
		System.out.println("to log only cookies from response");
		given()
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.log().cookies();
		
		System.out.println("to log only headers from response");
		given()
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.log().headers();
	}
}
