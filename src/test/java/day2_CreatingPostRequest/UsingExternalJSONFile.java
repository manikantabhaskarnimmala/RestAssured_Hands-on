package day2_CreatingPostRequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class UsingExternalJSONFile {

	Response response;
	String id;

	@Test(priority = 0)
	public void testPostRequestusingExternalFile() throws FileNotFoundException {

		File file = new File("src/test/resources/body.json");

		// Read the data from the file
		FileReader fr = new FileReader(file);

		JSONTokener jt = new JSONTokener(fr);

		JSONObject data = new JSONObject(jt);

		response = given().contentType("application/json").body(data.toString()).when()
				.post("http://localhost:3000/students");

		id = response.jsonPath().getString("id");

		response.then().statusCode(201)

				.body("name", equalTo("God")).body("location", equalTo("temple"))

				.body("otherNames[0]", equalTo("Hanuman")).body("otherNames[1]", equalTo("Chittaramma"))
				.body("otherNames[2]", equalTo("Venkateswara")).body("otherNames[3]", equalTo("Ayyappa"))

				.header("Content-Type", "application/json").log().all();
	}

	@Test(dependsOnMethods = "testPostRequestusingExternalFile")
	public void deleteStudent() {

		given()

				.when().delete("http://localhost:3000/students/"+id)
				.then().statusCode(200).log().all();

	}

}
