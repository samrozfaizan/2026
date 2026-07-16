package com.api.endpoints;

import static io.restassured.RestAssured.given;

import com.api.testcases.Routes;

import io.restassured.response.Response;



public class UserEndpoints {
	
	public static Response createUser() {
		Response res =given()
		
		      .when()
		      .post(Routes.postUrl);
		
		      return res;
	}
	

}
