package dev.yudin.YourPersonalFinancesBot.callbacks.expense;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component("MySumCallback")
public class MySumCallback {

	public SendMessage createCallback(String chatId,
									  String textMessage) {
		SendMessage sendMessage = new SendMessage();
		sendMessage.enableMarkdown(true);
		sendMessage.setChatId(chatId);
		sendMessage.setText(textMessage);
		return sendMessage;
	}
}
