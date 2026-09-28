package day2_CreatingPostRequest;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

public class UsingHashMap {

	@Test
	public void testPOSTusingHashMap() {

		Map data = new HashMap<>();
		data.put("fullName", "God");
		data.put("age", "15");
		data.put("class", "10");
		String gradeValues[] = { "99", "99", "99" };
		data.put("grades", gradeValues);
		String subjectValues[] = { "Math", "History", "Biology" };
		data.put("subjects", subjectValues);
		
		
		given()
			.contentType("application/json").body(data)
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201).log().all();

	}
	
	@Test	
	public void getStudentDetails() {
		
		given()
		.when()
			.get("http://localhost:3000/students/77OwQ_A_qv4")
		.then()
			.statusCode(200).log().all();
			
	}

}
