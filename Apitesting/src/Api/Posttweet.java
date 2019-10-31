package Api;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.restassured.path.json.JsonPath;

 

public class Posttweet {
	Properties prop= new Properties();
	Logger Log=Logger.getLogger("Posttweet");
	
	@BeforeTest
	public void start() throws IOException 
	{
		PropertyConfigurator.configure("C:\\Users\\Online Test\\git\\APItesting\\Apitesting\\Log4j.properties");
		FileInputStream fls= new FileInputStream("C:\\Users\\Online Test\\git\\APItesting\\Apitesting\\src\\data.properties");
        prop.load(fls);
	}
    
    @Test
    public void postTweet() {
    	  
	        String consumerKey = prop.getProperty("custermerKey");
	        String consumerSecret = prop.getProperty("cunsumerSecret");
	        String token = prop.getProperty("Token");
	        String tokenSecret = prop.getProperty("TokenSecret");
        
        RestAssured.baseURI= prop.getProperty("postbaseuri");
        Response res= given().auth().oauth(consumerKey, consumerSecret, token, tokenSecret).
                queryParam("status",prop.getProperty("tweet_value")).
                when().post(resource.postResource()).
                then().extract().response();
        
        String response= res.asString();
        Log.info(response);
        System.out.println(response);
        
        JsonPath js= new JsonPath(response); 
        String text= js.get("text").toString();
        Log.info(text);
        System.out.println(text);
        
        String id= js.get("id").toString();
        Log.info(id);
        System.out.println(id);
        
        //delete the tweet
       Response res1=  given().auth().oauth(consumerKey, consumerSecret, token, tokenSecret).
        when().post("/destroy/"+id+".json").
        then().extract().response();
       String response1= res1.asString();
       Log.info(response1);
       System.out.println(response1);
       
    }

 

}
