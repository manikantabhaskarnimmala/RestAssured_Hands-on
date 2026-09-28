package day2_CreatingPostRequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class UsingPOJO {

	Response response;
	String id;
	// Plain Old Java Object class
	@Test
	public void testPostUsingPOJOClass() {
		POJO_PostRequest data = new POJO_PostRequest();

		data.setFullName("Goddess");
		data.setAge(15);
		data.setStandard("10");
		int gradeValues[] = { 99,99,99 };
		String subjectValues[] = { "Math", "History", "Biology" };
		data.setGrades(gradeValues);
		data.setSubjects(subjectValues);
		 response=given()
				.contentType("application/json").body(data)
				.when()
					.post("http://localhost:3000/students");
		
		id=response.jsonPath().getString("id");

		
		response.then().statusCode(201)
			.body("fullName", equalTo("Goddess"))
			.body("age", equalTo(15))
			.body("standard", equalTo("10"))
			.body("grades[0]", equalTo(99))
			.body("grades[1]", equalTo(99))
			.body("grades[2]", equalTo(99))
			.body("subjects[0]", equalTo("Math"))
			.body("subjects[1]", equalTo("History"))
			.body("subjects[2]", equalTo("Biology"))
			.header("Content-Type", "application/json").log().all();
		
	}

	@Test(dependsOnMethods = "testPostUsingPOJOClass")
	public void deteleStudentDetails() {

		given()
		.when().delete("http://localhost:3000/students/"+id)
		.then().statusCode(200).log().all();

	}

}
