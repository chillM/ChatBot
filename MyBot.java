import java.io.IOException;

import org.jibble.pircbot.*;

public class MyBot extends PircBot{
	public MyBot() {
		this.setName("ChillBot2336");
	}
	
	public void onMessage(String channel, String sender, String login, String hostname, String message){
		if(message.equalsIgnoreCase("time") || message.equalsIgnoreCase("What is the time?")){
			String time = new java.util.Date().toString();
			sendMessage(channel, sender + ": The time is now " + time);
		}
		else if(message.equalsIgnoreCase("twitter")){
			//send trending Twitter tags
			//call the Twitter fetcher
			try{
				String m = TwitterFetcher.startWebRequest("DFW");
				sendMessage(channel, sender + ": " + m);
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		else if(message.substring(0, 7).equalsIgnoreCase("weather") || message.substring(0, 20).equalsIgnoreCase("how's the weather in")){
			//send the weather using the weather fetcher
			//get the zip code
			String zip = "73125";
			if(message.length() <= 18){
				zip = message.substring(message.length() - 5, message.length());
			}
			else{
				zip = message.substring(message.length() - 6, message.length() - 1);
			}
			
			//validate the zip
			
			
			//call the weather fetcher
			try {
				String m = WeatherFetcher.startWebRequest(zip);
				sendMessage(channel, sender + ": " + m);
			}
			catch (IOException e){
				e.printStackTrace();
			}
		}
		
		
	}
}