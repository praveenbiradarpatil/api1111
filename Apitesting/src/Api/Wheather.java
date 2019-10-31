package Api;
import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Wheather {
	Properties prop= new Properties();
	Logger Log=Logger.getLogger(" Wheather");
	
	@BeforeTest
	public void start() throws IOException 
	{
		PropertyConfigurator.configure("C:\\Users\\Online Test\\git\\APItesting\\Apitesting\\Log4j.properties");
		FileInputStream fls= new FileInputStream("C:\\Users\\Online Test\\git\\APItesting\\Apitesting\\src\\data.properties");
        prop.load(fls);
	}
	
    @Test
	public void searchtweet() 
	{
	        String consumerKey = prop.getProperty("custermerKey");
	        String consumerSecret = prop.getProperty("cunsumerSecret");
	        String token = prop.getProperty("Token");
	        String tokenSecret = prop.getProperty("TokenSecret");
		
		RestAssured.baseURI=prop.getProperty("wheatherbaseuri");
		Response res=given().auth().oauth(consumerKey, consumerSecret, token, tokenSecret).
		queryParam("q","Bangalore Weather").
		when().get(resource.retweetResource()).then().extract().response();
		
		
		String responce=res.asString();//to convert into string
		Log.info(responce);
		System.out.println(responce);
		
		
	
		
	
}
}
