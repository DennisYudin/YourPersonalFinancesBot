package dev.yudin.yourpersonalfinancesbot.handlers.main_menu;

import dev.yudin.yourpersonalfinancesbot.callbacks.Callback;
import dev.yudin.yourpersonalfinancesbot.keyboards.Keyboard;
import dev.yudin.yourpersonalfinancesbot.states_handler.Handler;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@Log4j
@Component("MainMenuState")
public class MainMenuHandler implements Handler {

	public static final String TEXT_MESSAGE = "Choose category";

	private Keyboard keyboard;
	private Callback callback;

	@Autowired
	public MainMenuHandler(@Qualifier("MainMenuKeyboard") Keyboard keyboard,
						   @Qualifier("MainMenuCallback") Callback callback) {
		this.keyboard = keyboard;
		this.callback = callback;
	}

	@Override
	public SendMessage apply(Update update) {
		String userId;
		if (update.hasMessage()) {
			userId = update.getMessage().getFrom().getId().toString();
		} else {
			userId = update.getCallbackQuery().getFrom().getId().toString();
		}
		InlineKeyboardMarkup keyboardWithButtons = keyboard.create();

		SendMessage callbackWithKeyboard = callback.create(userId, TEXT_MESSAGE, keyboardWithButtons);

		return callbackWithKeyboard;
	}
}
