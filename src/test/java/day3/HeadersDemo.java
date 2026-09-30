package day3;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeadersDemo {
	
	@Test(priority = 0)
	public void testHeaders() {
		
		Response response=given().when().get("https://www.google.com/");
		
		//Headers information validation
		response.then().header("Content-Type", "text/html; charset=ISO-8859-1")
		.header("Content-Encoding", "gzip").header("Server", "gws");
		
		//Get single header information from headers		
		String header = response.getHeader("Content-Type");
		System.out.println("Content-Type header value: "+header);
		
		//Get all the headers info		
		Headers myHeaders=response.getHeaders();
		//Here Headers is not hashMap which is not a HashMap but the values in headers are stored in name and values

		for (Header hd : myHeaders) {
		    System.out.println(hd.getName() + " : " + hd.getValue());
		}
	}

}
