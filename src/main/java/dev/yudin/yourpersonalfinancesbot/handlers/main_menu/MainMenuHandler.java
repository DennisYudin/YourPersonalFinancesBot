package dev.yudin.yourpersonalfinancesbot.handlers.main_menu;

import dev.yudin.yourpersonalfinancesbot.callbacks.Callback;
import dev.yudin.yourpersonalfinancesbot.keyboards.Keyboard;
import dev.yudin.yourpersonalfinancesbot.states_handler.Handler;
import lombok.extern.log4j.Log4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;


@Component("MainMenuState")
public class MainMenuHandler implements Handler {
	private static final Logger log = LogManager.getLogger();
	public static final String TEXT_MESSAGE = "Choose option";
	private Keyboard keyboard;
	private Callback callback;

	@Autowired
	public MainMenuHandler(@Qualifier("MainMenuKeyboard") Keyboard keyboard,
						   @Qualifier("MainMenuCallback") Callback callback) {
		this.keyboard = keyboard;
		this.callback = callback;
	}

	@Override
	public SendMessage handle(Update update) {
		String userId;
		if (update.hasMessage()) {
			userId = update.getMessage().getFrom().getId().toString();
		} else {
			userId = update.getCallbackQuery().getFrom().getId().toString();
		}
		log.info("CALL handle() for user {}", userId);

		InlineKeyboardMarkup keyboardWithButtons = keyboard.create();

		SendMessage response = callback.create(userId, TEXT_MESSAGE, keyboardWithButtons);
		log.info("Response: {}\n", response);
		return response;
	}
}
