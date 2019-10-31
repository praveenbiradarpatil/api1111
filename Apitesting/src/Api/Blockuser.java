package Api;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Blockuser {
	Properties prop=new Properties();
	Logger Log=Logger.getLogger("Blockuser");
	
	  @BeforeTest
	  public void begin() throws IOException {
	  PropertyConfigurator.configure("C:\\Users\\Online Test\\git\\APItesting\\Apitesting\\Log4j.properties");
	  FileInputStream f=new FileInputStream("C:\\Users\\Online Test\\git\\APItesting\\Apitesting\\src\\data.properties");
	  prop.load(f);
	  }
	 
	  @Test
	  public void start()
	  {
		     String consumerKey = prop.getProperty("custermerKey");
		        String consumerSecret = prop.getProperty("cunsumerSecret");
		        String token = prop.getProperty("Token");
		        String tokenSecret = prop.getProperty("TokenSecret");
		
		RestAssured.baseURI=prop.getProperty("blockuseruri");
		Response res= given().auth().oauth(consumerKey,consumerSecret,token,tokenSecret).
	    queryParam("screen_name","Praveen19553459").
	 
		when().post(resource.postResource2()).then().extract().response();     
	     String response = res.asString();
	     Log.info(response);
	     System.out.println(response);
	     
	     
	     JsonPath js=new JsonPath(response);
		
	     String id=js.getString("id");
	     Log.info(id);
		 System.out.println(id);
		 
		 String text=js.getString("screen_name");
		 Log.info(text);
		System.out.println(text);
				
				
	  }			
}

