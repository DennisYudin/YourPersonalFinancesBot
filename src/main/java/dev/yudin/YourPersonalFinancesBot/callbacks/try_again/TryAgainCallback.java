package dev.yudin.YourPersonalFinancesBot.callbacks.try_again;

import dev.yudin.YourPersonalFinancesBot.callbacks.Callback;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@Component("TryAgainCallback")
public class TryAgainCallback implements Callback {

	@Override
	public SendMessage create(String userId, String textMessage, InlineKeyboardMarkup keyboard) {
		SendMessage sendMessage = new SendMessage();
		sendMessage.enableMarkdown(true);
		sendMessage.setChatId(userId);
		sendMessage.setText(textMessage);
		if (keyboard != null) {
			sendMessage.setReplyMarkup(keyboard);
		}
		return sendMessage;
	}
}
