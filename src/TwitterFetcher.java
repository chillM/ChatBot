import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.*;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TwitterFetcher {
    
	static String startWebRequest(String location) throws IOException
    {
		String bearerToken = "AAAAAAAAAAAAAAAAAAAAAMYtyAAAAAAA3b7jTcC6W0UQgEqvZ1NUFh8b%2FY8%3DN5D6TMoFd756HAoqIAllzSIeACd9qjYPLEbUSYDD5DuIvOHYgm";
		//convert from location to id
		int id = 2388929;
		
		//make web request
        String twitterURL = "https://api.twitter.com/1.1/trends/place.json?id=" + id;
//        String twitterURL = "https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=twitterapi&count=2";
          StringBuilder result = new StringBuilder();
          URL url = new URL(twitterURL);
          HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
          conn.setDoInput(true);
          conn.setDoOutput(true);
          conn.setRequestMethod("GET");
          conn.setRequestProperty("Host", "api.twitter.com");
          conn.setRequestProperty("User-Agent", "apiTrends");
          conn.setRequestProperty("Authorization", "Bearer " + bearerToken);
          conn.setUseCaches(false);
          
          // Parse the JSON response into a JSON mapped object to fetch fields from.
          JSONArray obj = (JSONArray)JSONValue.parse(readResponse(conn));
          JSONArray trends = (JSONArray) ((JSONObject)obj.get(0)).get("trends");
          
          System.out.println(trends.get(0).toString());
          System.out.println(trends.get(1).toString());
          
          if (obj != null) {
        	  StringBuilder tweet = new StringBuilder();
        	  //format message for output
        	  for(int i = 0;i < trends.size(); i++){
        		  tweet.append(((JSONObject)trends.get(i)).get("name").toString() + "   ");
        	  }
        	  //String tweet = ((JSONObject)trends.get(0)).get("name").toString();
        	  
        	  String tweetString = tweet.toString();
        	  
          return (tweetString != null) ? tweetString : "";
          }
          
          return new String();
    }
	
    
 // Reads a response for a given connection and returns it as a string.
    private static String readResponse(HttpsURLConnection connection) {
    	try{
			
			StringBuilder str = new StringBuilder();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = "";
			while((line = br.readLine()) != null) {
			str.append(line + System.getProperty("line.separator"));
			}
			System.out.println(str.toString());
			return str.toString();
    	}
    	catch (IOException e) { return new String(); }
    }
}