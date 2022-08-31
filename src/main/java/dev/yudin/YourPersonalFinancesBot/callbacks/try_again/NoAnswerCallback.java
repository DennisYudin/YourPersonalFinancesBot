package dev.yudin.YourPersonalFinancesBot.callbacks.try_again;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component("NoAnswerCallback")
public class NoAnswerCallback {

	public SendMessage createCallback(String chatId,
									  String textMessage) {
		SendMessage sendMessage = new SendMessage();
		sendMessage.enableMarkdown(true);
		sendMessage.setChatId(chatId);
		sendMessage.setText(textMessage);
		return sendMessage;
	}
}
