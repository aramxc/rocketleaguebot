package discordBot;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

public class Connection {
	
	/*
	 * Main method where program starts
	 */
	public static void main(String[] args) {
		//Construct a builder for a BOT account		
		try {
			JDA jda = new JDABuilder(AccountType.BOT)
					.setToken(Constants.discordToken) // Token of account, stored in Constants.java
					.addEventListener(new MessageResponder()) //Instance of class that will handle events
					.buildBlocking(); //Guarantees JDA will be completely loaded
			
		} catch (LoginException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (RateLimitedException e) {
			e.printStackTrace();
		}
	
	}

}
