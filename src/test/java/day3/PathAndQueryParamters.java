package day3;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

public class PathAndQueryParamters {

	//https://reqres.in/api/users?page=2
	
	@Test
	void testPathAndQueryParamters() {
		
		given()
			.pathParam("myPath", "users") //Path parameter
			.queryParam("page", 2) //query parameter
		.when()
			.get("https://reqres.in/api/{myPath}")
		
		.then()
			.statusCode(200).log().all();
		
	}

}
