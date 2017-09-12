package discordBot;

import java.util.Random;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageResponder extends ListenerAdapter {
	
	static String SteamID = null;

	/*
	 *  Method where the program starts.
	 */
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		
		/*
		 * Typical JDA code
		 */
		JDA jda = event.getJDA(); //Core of API
		long responseNumber = event.getResponseNumber(); //Amount of discord events JDA has received since last reconnect
		
		/*
		 * Event specific code
		 */
		User author = event.getAuthor(); //User that sends message
		Message message = event.getMessage(); //Message that is received
		MessageChannel channel = event.getChannel(); //Message channel that message was sent to
		
		String msg = message.getContent(); //Returns readable version of the message.
		
		boolean bot = author.isBot(); //determines if user is a bot or not
		
		//begin message responses
		if (msg.equals("!ping")) {
			channel.sendMessage("pong!").queue();
				
		} else if (msg.equals("!roll")) {
			Random random = new Random();
			int roll = random.nextInt(6) + 1;
			channel.sendMessage("Your roll: " + roll).queue(sentMessage -> 
			{
				if (roll < 3) {			
					channel.sendMessage( "Your roll wasn't very good... must be bad luck!\n").queue();
				} 
			
			});
		} else if (msg.equals("!hello")) {
			channel.sendMessage("Howdy!").queue();
						
		} else if (msg.endsWith("rlinfo")) {
			int msgEnd = msg.indexOf("rlinfo");
			if (msgEnd != -1) {
				SteamID = msg.substring(1,msgEnd);
			channel.sendMessage(SteamID).queue();
			}

			
		}
	
	}
	
}

