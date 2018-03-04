import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
 
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
 
public class WeatherFetcher
{
	
   /*
    public static void main(String[] args)
    {
        try {
            startWebRequest("73120");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    */
   
    static String startWebRequest(String zip) throws IOException
    {
        String weatherURL = "http://api.openweathermap.org/data/2.5/weather?zip=" + zip + ",us&APPID=26aa1d90a24c98fad4beaac70ddbf274";
       
          StringBuilder result = new StringBuilder();
          URL url = new URL(weatherURL);
          HttpURLConnection conn = (HttpURLConnection) url.openConnection();
          conn.setRequestMethod("GET");
          BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
          String line;
          while ((line = rd.readLine()) != null) {
             result.append(line);
          }
          rd.close();
          System.out.println(result.toString());
         
         String message =  parseJson(result.toString());
         
         System.out.println(message);
         
          return message;
       }
   
    static String parseJson(String json)
    {
        JsonElement jelement = new JsonParser().parse(json);
        JsonObject  MasterWeatherObject = jelement.getAsJsonObject();
       
        JsonObject  coordinateObject = MasterWeatherObject.getAsJsonObject("main");
        double  temp = coordinateObject.get("temp").getAsDouble();
        double tempMin = coordinateObject.get("temp_min").getAsDouble();
        double tempMax = coordinateObject.get("temp_max").getAsDouble();
        
        //convert to Celsius from Kelvin
        temp -= 273.15;
        tempMin -= 273.15;
        tempMax -= 273.15;
        
        //convert from Celsius to Fahrenheit
        temp = temp * 9 / 5 + 32;
        tempMin = tempMin * 9 / 5 + 32;
        tempMax = tempMax * 9 / 5 + 32;
        
        
        return "The weather's going to be " + temp + " with a high of " + tempMax + " and a low of " + tempMin + ".";
       
       
       
    }
       
    }