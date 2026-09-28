package day2_CreatingPostRequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class UsingJsonLibrary {

//POST request body using or.json library
	Response response;
	String id;
	
    @Test
    public void testPostUsingJsonLibrary() {

        JSONObject data = new JSONObject();

        data.put("fullName", "God");
	    data.put("age", 15);
	    data.put("standard", "10");

	    int gradeValues[] = { 99,99,99 };
	    data.put("grades", gradeValues);

	    String subjectValues[] = { "Math", "History", "Biology" };
	    data.put("subjects", subjectValues);

	    response =given()
	        .contentType("application/json")
	        .body(data.toString())
	    .when()
	        .post("http://localhost:3000/students");
	    
	    id=response.jsonPath().getString("id");
	    
	    response.then()
	        .statusCode(201)

	        .body("fullName", equalTo("God"))
	        .body("age", equalTo(15))
	        .body("standard", equalTo("10"))

	        .body("grades[0]", equalTo(99))
	        .body("grades[1]", equalTo(99))
	        .body("grades[2]", equalTo(99))

	        .body("subjects[0]", equalTo("Math"))
	        .body("subjects[1]", equalTo("History"))
	        .body("subjects[2]", equalTo("Biology"))

	        .header("Content-Type", "application/json")
	        .log().all();
    }
    
    @Test(dependsOnMethods = "testPostUsingJsonLibrary")
	public void deteleStudentDetails() {

		given()
		.when().delete("http://localhost:3000/students/"+id)
		.then().statusCode(200).log().all();

	}
}