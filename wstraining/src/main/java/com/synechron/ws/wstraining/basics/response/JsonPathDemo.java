package com.synechron.ws.wstraining.basics.response;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JsonPathDemo {
	public String baseurl = "https://api.trello.com";
	public String key = "33fe6a197674af91fb157d4a447c6711";
	public String token = "6e140bb254e01e08601a7e643df5010e705e48c77f7fa525d7602b85cfbe2b98";
	
	@Test
	public void validateBodyInGet() {
		RestAssured.baseURI = baseurl;
		Response response = RestAssured.given().
								param("key", key).
								param("token", token).
							when().
								get("/1/boards/nmnGejy8").
							then().
								assertThat().statusCode(200).
								extract().response();
		String responseStr = response.asString();
		JsonPath responseBody	 = new JsonPath(responseStr);		
		System.out.println("ID: "+responseBody.get("id"));
		System.out.println("ID: "+responseBody.get("name"));
		Assert.assertEquals(responseBody.get("id"), "60ab8ac20cf3a5522f745ba9");
		Assert.assertEquals(responseBody.get("name"), "myAutomationBoard");
	}
	@Test
	public void validateBodyArrayInGet() {
		RestAssured.baseURI = baseurl;
		Response response = RestAssured.given().
								param("key", key).
								param("token", token).
							when().
								get("/1/boards/nmnGejy8").
							then().
								assertThat().statusCode(200).
								extract().response();
		String responseStr = response.asString();
		JsonPath responseBody	 = new JsonPath(responseStr);		
		int size = responseBody.get("prefs.backgroundImageScaled.size()");
		System.out.println(size);
		for(int i=0; i<size;i++) {
			System.out.println("Width at Index is : "+ i +" is :" + responseBody.get("prefs.backgroundImageScaled["+ i +"].width"));
			System.out.println("Height at Index is : "+ i +" is :" + responseBody.get("prefs.backgroundImageScaled["+ i +"].height"));
			System.out.println("URL at Index is : "+ i +" is :" + responseBody.get("prefs.backgroundImageScaled["+ i +"].url"));
		}
	}
}
