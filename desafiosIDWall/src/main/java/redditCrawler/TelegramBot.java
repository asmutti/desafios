package redditCrawler;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot
{

	public String getBotUsername() {
		// return bot username
		//if bot username is @MyAmazingBot, it must return 'MyAmazingBot'
		
		return "TelegramBot";
	}

	public void onUpdateReceived(Update update) {
		// We check if the update has a message and the message has text
	    if (update.hasMessage() && update.getMessage().hasText()) {
	        // Set variables
	        String message_text = update.getMessage().getText();
	        long chat_id = update.getMessage().getChatId();

	        SendMessage message = new SendMessage() // Create a message object object
	                .setChatId(chat_id)
	                .setText(message_text);
	        try {
	            execute(message); // Sending our message object to user
	        } catch (TelegramApiException e) {
	            e.printStackTrace();
	        }
	    }
		
	}

	@Override
	public String getBotToken() {
		// Return bot token from BotFather
        return "12345:qwertyuiopASDGFHKMK";
	}

}
