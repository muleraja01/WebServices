package com.synechron.ws.wstraining.basics.response;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class JayWayDemo {
	public String baseurl = "https://api.trello.com";
	public String key = "33fe6a197674af91fb157d4a447c6711";
	public String token = "6e140bb254e01e08601a7e643df5010e705e48c77f7fa525d7602b85cfbe2b98";
	public Response response=null;
	@Test
	public void createValidateResponseUsingJayway() {
		RestAssured.baseURI = baseurl;
		response = RestAssured.given().
								param("key", key).
								param("token", token).
							when().
								get("/1/boards/nmnGejy8").
							then().
								assertThat().statusCode(200).extract().response();
	}
	
	@Test(dependsOnMethods="createValidateResponseUsingJayway")
	public void getID() {
		String id = JsonPath.read(response.asString(), "$.id");
		String name = JsonPath.read(response.asString(), "$.nameS");
		System.out.println("The Id is :" +id);
		System.out.println("The name is :" +name);
	}
	@Test(dependsOnMethods="createValidateResponseUsingJayway")
	public void getArray() {
		List<Map<String, String>> prefArray = JsonPath.read(response.asString(), "$.prefs.backgroundImageScaled");
		//Print using for Each loop
		for (Map<String, String>map: prefArray) {
			System.out.println(map);
		}
		//Print using for loop
		for(int i=0;i<prefArray.size();i++) {
			Map<String, String> map = prefArray.get(i);
			System.out.println(map);
		}
	}
	
}
