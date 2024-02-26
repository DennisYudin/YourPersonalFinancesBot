package dev.yudin.yourpersonalfinancesbot.callbacks;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@Slf4j
@Component("MainMenuCallback")
public class MainMenuCallback implements Callback {

	@Override
	public SendMessage create(String userId,
							  String textMessage,
							  InlineKeyboardMarkup keyboard) {

		SendMessage callBack = new SendMessage();
		callBack.enableMarkdown(true);
		callBack.setChatId(userId);
		callBack.setText(textMessage);
		callBack.setReplyMarkup(keyboard);

		return callBack;
	}
}
