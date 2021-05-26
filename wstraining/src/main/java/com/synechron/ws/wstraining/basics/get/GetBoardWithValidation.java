package com.synechron.ws.wstraining.basics.get;

import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.ResponseSpecificationImpl.HamcrestAssertionClosure;

import static io.restassured.RestAssured.*;

public class GetBoardWithValidation {

	public String baseurl = "https://api.trello.com";
	public String key = "33fe6a197674af91fb157d4a447c6711";
	public String token = "6e140bb254e01e08601a7e643df5010e705e48c77f7fa525d7602b85cfbe2b98";
	
	@Test
	public void validateBodyInGet()
	{
		RestAssured.baseURI = baseurl;
		given().
			param("key", key).
			param("token", token).
		when().
			get("/1/boards/nmnGejy8"). 
		then(). 
			assertThat().statusCode(200).
			and(). 
				contentType(ContentType.JSON). 
			and().
				body("id", equalTo("60ab8ac20cf3a5522f745ba9")).
			and().
				body("name", equalTo("myAutomationBoard"));
	
	}
	
	
	@Test
	public void validateResponseHeaderInGet()
	{
		RestAssured.baseURI = baseurl;
		given().
			param("key", key).
			param("token", token).
		when().
			get("/1/boards/nmnGejy8"). 
		then(). 
			assertThat().statusCode(200).
			and(). 
				contentType(ContentType.JSON). 
			and().
				header("Content-Type", "application/json; charset=utf-8"). 
			and(). 
				header("Expires", "Thu, 01 Jan 1970 00:00:00"). 
			and(). 
				body("id", equalTo("60ab8ac20cf3a5522f745ba9")).
			and().
				body("name", equalTo("myAutomationBoard"));
	
	}
}