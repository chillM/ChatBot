import org.jibble.pircbot.*;

public class MyBotMain {
	
	public static void main(String args[]) throws Exception{
		
		//Now start our bot up
		MyBot bot = new MyBot();
		
		//enable debugging output
		bot.setVerbose(true);
		
		//connect to the IRC server
		bot.connect("irc.freenode.net");
		
		//join the #pircbot channel
		bot.joinChannel("#irchacks");
		
	}
}
