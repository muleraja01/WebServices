package com.synechron.ws.wstraining.basics.get;



import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;


public class GetTrelloBoard {

	public String baseurl="https://api.trello.com";
	public String key="33fe6a197674af91fb157d4a447c6711";
	public String token="6e140bb254e01e08601a7e643df5010e705e48c77f7fa525d7602b85cfbe2b98";
	@Test
	public void validateGetBoardInBDDStyle() {
			RestAssured.
			given()
				.param("key", "33fe6a197674af91fb157d4a447c6711")
				.param("token", "6e140bb254e01e08601a7e643df5010e705e48c77f7fa525d7602b85cfbe2b98")
			.when()
				.get("https://api.trello.com/1/boards/nmnGejy8")
			.then()
				.assertThat().statusCode(200);
	}
	
	@Test
	public void validateGetBoardInStyleSimplified() {
		String baseurl="https://api.trello.com";
		RestAssured.baseURI=baseurl;
			RestAssured.
			given()
				.param("key", key)
				.param("token", token)
			.when()
				.get("/1/boards/nmnGejy8")
			.then()
				.assertThat().statusCode(200);
	}
	
	@Test
	public void validateGetBoardInRestFormat() {
		RestAssured.baseURI=baseurl;
			RequestSpecification reqSpecification = RestAssured.given();
			reqSpecification.param("key", key);
			reqSpecification.param("token", token);
			Response response = reqSpecification.get("/1/boards/nmnGejy8");
			 ValidatableResponse valRes = response.then();
			 valRes.assertThat().statusCode(200);
	}
	
	@Test
	public void validateGetBoardInExpectFormat()
	{
		RestAssured.baseURI = baseurl;
		
		RestAssured.
			given()
				.param("key", key)
				.param("token", token)
			.expect().statusCode(200).
			when().get("/1/boards/nmnGejy8");
	}
}
