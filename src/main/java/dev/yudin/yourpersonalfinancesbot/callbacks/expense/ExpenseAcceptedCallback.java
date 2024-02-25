package dev.yudin.yourpersonalfinancesbot.callbacks.expense;


import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class ExpenseAcceptedCallback {

	public SendMessage createCallback(String chatId,
									  String textMessage) {
		SendMessage sendMessage = new SendMessage();
		sendMessage.enableMarkdown(true);
		sendMessage.setChatId(chatId);
		sendMessage.setText(textMessage);
		return sendMessage;
	}
}
