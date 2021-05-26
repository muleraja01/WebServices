package com.synechron.ws.wstraining.basics.response;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class ValidateReponseDemo {

	public String baseurl = "https://api.trello.com";
	public String key = "33fe6a197674af91fb157d4a447c6711";
	public String token = "6e140bb254e01e08601a7e643df5010e705e48c77f7fa525d7602b85cfbe2b98";
	public ValidatableResponse validateReposnse=null;
	@Test
	public void createValidateResponseTest() {
		RestAssured.baseURI = baseurl;
		validateReposnse = RestAssured.given().
								param("key", key).
								param("token", token).
							when().
								get("/1/boards/nmnGejy8").
							then().
								assertThat().statusCode(200);
		String id = validateReposnse.extract().path("id");
		String name = validateReposnse.extract().path("name");
		System.out.println("ID  Is :"+id);
		System.out.println("Name  Is :"+name);
	}
	
	@Test(dependsOnMethods="createValidateResponseTest")
	public void getNodeWithConditionTest() {
		Map<String, String> node = validateReposnse.extract().path("prefs.backgroundImageScaled.find {it.width == 140}");
		System.out.println("Node: "+node);

	}
	@Test(dependsOnMethods="createValidateResponseTest")
	public void getHeightWithConditionTest() {
		int height = validateReposnse.extract().path("prefs.backgroundImageScaled.find{it.width == 140}.height");
		System.out.println(height);

	}
	@Test(dependsOnMethods="createValidateResponseTest")
	public void getURLWithConditionTest() {
		String url = validateReposnse.extract().path("prefs.backgroundImageScaled.find{it.width == 140}.url");
		System.out.println(url);

	}
	
	//
	
	@Test(dependsOnMethods="createValidateResponseTest")
	public void getNodeWithMaxWithConditionTest() {
		Map<String, String> max = validateReposnse.extract().path("prefs.backgroundImageScaled.max{it.width}");
		System.out.println("Max with Node: "+max);

	}
	
	@Test(dependsOnMethods="createValidateResponseTest")
	public void getNodeWithMinUrlWithConditionTest() {
		String minurl  = validateReposnse.extract().path("prefs.backgroundImageScaled.min{it.width}.url");
		System.out.println("min with URL: "+minurl);

	}
}
