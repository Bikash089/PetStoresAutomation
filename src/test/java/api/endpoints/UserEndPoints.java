package api.endpoints;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;



//Created to perform CRUD operations on user API

public class UserEndPoints {
	
	public static Response createUser(User payload) {
		
		Response response = given()
		 .accept(ContentType.JSON)
		 .contentType(ContentType.JSON)
		 .body(payload)
		
		.when()
		 .post(Routes.post_url);
		
		return response;
		
	}

public static Response readUser(String userName) {
		
		Response response = given()
		.pathParam("username", userName)
		
		.when()
		 .get(Routes.get_url);
		
		return response;
		
	}

public static Response updateUser(User payload, String userName) {
	
	Response response = given()
	.pathParam("username", userName)
	.contentType(ContentType.JSON)
	.accept(ContentType.JSON)
	.body(payload)
	
	.when()
	 .put(Routes.put_url);
	
	return response;
	
}

public static Response deleteUser(String userName) {
	
	Response response = given()
	.pathParam("username", userName)
	
	.when()
	 .delete(Routes.get_url);
	
	return response;
	
}
}
