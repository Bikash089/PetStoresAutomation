package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	User userPayLoad;
	
	public Logger logger;
	
	@BeforeClass
	public void setupData() {
		
		faker = new Faker();
		userPayLoad = new User();
		
		userPayLoad.setId(faker.idNumber().hashCode());
		userPayLoad.setUsername(faker.name().username());
		userPayLoad.setFirstName(faker.name().firstName());
		userPayLoad.setLastName(faker.name().lastName());
		userPayLoad.setEmail(faker.internet().safeEmailAddress());
		userPayLoad.setPassword(faker.internet().password(5,10));
		userPayLoad.setPhone(faker.phoneNumber().cellPhone());
		
		logger = LogManager.getLogger(this.getClass());
	}	
		@Test(priority=1)
		public void testPostUser() {
			
			logger.info("****Creating Users********");
			
			Response response=UserEndPoints.createUser(userPayLoad);
			response.then().log().all();
			
			Assert.assertEquals(response.getStatusCode(), 200);
			
			logger.info("****Users Created********");
		}
		
		
		@Test(priority=2)
		public void testGetUserByName() {
			
			logger.info("****Getting Users********");
			
			Response response=UserEndPoints.readUser(this.userPayLoad.getUsername());
			response.then().log().all();
		    Assert.assertEquals(response.getStatusCode(),200);	
		    
		    logger.info("****User Details Retrieved********");
		}
		
		@Test(priority=3)
		public void testUpdateUserByName() {
			
			logger.info("****Updating Users********");
             
			//update data using payload
			userPayLoad.setFirstName(faker.name().firstName());
			userPayLoad.setLastName(faker.name().lastName());
			userPayLoad.setEmail(faker.internet().safeEmailAddress());
			
			Response response = UserEndPoints.updateUser(userPayLoad, this.userPayLoad.getUsername());
			response.then().log().all();
			
			Assert.assertEquals(response.getStatusCode(), 200);
			
			//checking data after update
			Response responseAfterUpdate=UserEndPoints.readUser(this.userPayLoad.getUsername());
			response.then().log().all();
			
			Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
			
			logger.info("****Updated Users********");
			
			
		}
		
		@Test(priority=4)
		public void testDeleteUser() {
			
			logger.info("****Deleting Users********");
			
			Response response = UserEndPoints.deleteUser(this.userPayLoad.getUsername());
			
			Assert.assertEquals(response.getStatusCode(), 200);
			
			logger.info("****Deleted Users********");
			
		}
		
	}


