package day2_CreatingPostRequest;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.annotations.Test;

public class UsingJsonLibrary {

    @Test
    public void testPostUsingJsonLibrary() {

        JSONObject data = new JSONObject();

        data.put("fullName", "Goddess");
        data.put("age", "15");
        data.put("class", "10");

        String gradeValues[] = { "100", "99", "99" };
        data.put("grades", gradeValues);

        String subjectValues[] = { "Math", "History", "Biology" };
        data.put("subjects", subjectValues);

        given()
            .contentType("application/json")
            .body(data.toString())
        .when()
            .post("http://localhost:3000/students")
        .then()
            .statusCode(201)
            .log().all();
    }
}