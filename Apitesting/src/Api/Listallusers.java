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
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Listallusers {
	Properties prop= new Properties();
	Logger Log=Logger.getLogger("Listallusers");
	
	@BeforeTest
	public void start() throws IOException 
	{
		PropertyConfigurator.configure("C:\\Users\\Online Test\\git\\APItesting\\Apitesting\\Log4j.properties");
		FileInputStream fls= new FileInputStream("C:\\Users\\Online Test\\git\\APItesting\\Apitesting\\src\\data.properties");
        prop.load(fls);
	}
	
	@Test
    public void listusers() 
    { 
	        String consumerKey = prop.getProperty("custermerKey");
	        String consumerSecret = prop.getProperty("cunsumerSecret");
	        String token = prop.getProperty("Token");
	        String tokenSecret = prop.getProperty("TokenSecret");
    	
        RestAssured.baseURI=prop.getProperty("listbaseuri");
        Response res=given().auth().oauth( consumerKey, consumerSecret, token, tokenSecret).
                param("q","Qualitest").
        when().get(resource.retweetResource()).then().assertThat().statusCode(200).and().contentType(ContentType.JSON).extract().response();
        String response=res.asString();
        Log.info(response);
        System.out.println(response);
        
        JsonPath js=new JsonPath(response);
        int count=js.get("statuses.size()");
        Log.info(count);
        System.out.println(count);
        for(int i=0;i<count;i++)
        {
            String user=js.get("statuses["+i+"].user.screen_name");
            System.out.println("User name - "+user);
            Log.info(user);
        }
    }
}


