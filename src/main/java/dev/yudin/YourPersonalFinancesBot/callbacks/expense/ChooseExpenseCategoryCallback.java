package dev.yudin.YourPersonalFinancesBot.callbacks.expense;

import dev.yudin.YourPersonalFinancesBot.callbacks.Callback;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@Component("ChooseExpenseCategoryCallback")
public class ChooseExpenseCategoryCallback implements Callback {

	@Override
	public SendMessage create(String userId,
							  String textMessage,
							  InlineKeyboardMarkup keyboard) {
		SendMessage sendMessage = new SendMessage();
		sendMessage.enableMarkdown(true);
		sendMessage.setChatId(userId);
		sendMessage.setText(textMessage);
		sendMessage.setReplyMarkup(keyboard);
		return sendMessage;
	}
}
