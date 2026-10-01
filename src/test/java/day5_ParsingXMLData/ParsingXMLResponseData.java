package day5_ParsingXMLData;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParsingXMLResponseData {
	Response response;
	
	@Test
	public void testXMLParse() {
		
		//approach 1: For the Static data
		given()
			.accept(ContentType.XML)
		.when()
			.get("https://gorest.in/public/v2/users?page=1&per_page=5")
		.then()
			.header("Content-Type", "application/xml; charset=utf-8")
			.body("users.user[0].name", equalTo("Aarav Sharma"))
			.log().all();
	}
	
	@Test
	public void testXmlData() {
		// Approach2: validation response by using the response variable

		response = given().accept(ContentType.XML).when().get("https://gorest.in/public/v2/users?page=1&per_page=5/");

		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.contentType(), "application/xml; charset=utf-8");
		
		String name=response.xmlPath().get("users.user[0].name").toString();
		Assert.assertEquals(name, "Aarav Sharma","Value is different from what we are expecting");
		
		String lastEmail = response.xmlPath().get("users.user[4].email").toString();
		Assert.assertEquals(lastEmail, "vikram.nair@example.com");
		
		response.then().log().all();
	}
	
	//Approach3 using XmlPath class
	@Test
	public void testXmlDynamicData() {
		response = given().accept(ContentType.XML).queryParam("page", "1").queryParam("per_page", "5") 
				.when().get("https://gorest.in/public/v2/users");
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println(response.then().log().all());
		XmlPath xmlObj = new XmlPath(response.body().asString());
		
		//By using the getList(), we can obtain the nodes of the similar type
		
		List<String> usersInfo=xmlObj.getList("users.user");
		Assert.assertEquals(usersInfo.size(), 5); //5 users
		
		//info of all the users
		for(String data: usersInfo) {
			System.out.println(data);
		}
		
		//to retrieve all the names of the users		
		List<String> userNames = xmlObj.getList("users.user.name");		
		for(String name : userNames) {
			System.out.println(name);
		}
		
		//Find the specific value
		boolean status =false;
		for(String name: userNames) {
			if(name.equals("Aarav Sharma")) {
				status=true;
				break;
			}
		}
		
		Assert.assertEquals(status, true, "Specifc value is not avaialble");
		
	}

}
