package dev.yudin.yourpersonalfinancesbot.handlers.add_expense;

import dev.yudin.yourpersonalfinancesbot.cache.Cache;
import dev.yudin.yourpersonalfinancesbot.callbacks.Callback;
import dev.yudin.yourpersonalfinancesbot.enums.State;
import dev.yudin.yourpersonalfinancesbot.keyboards.Keyboard;
import dev.yudin.yourpersonalfinancesbot.states_handler.Handler;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Log4j
@Component("ChooseExpenseCategoryState")
public class ChooseExpenseCategoryHandler implements Handler {

	public static final String TEXT_MESSAGE = "Choose expense category";

	private Keyboard keyboard;
	private Callback callback;
	private Cache cache;

	@Autowired
	public ChooseExpenseCategoryHandler(@Qualifier("ChooseExpenseCategoryKeyboard") Keyboard keyboard,
										@Qualifier("ChooseExpenseCategoryCallback") Callback callback,
										Cache cache) {
		this.keyboard = keyboard;
		this.callback = callback;
		this.cache = cache;
	}

	@Override
	public SendMessage apply(Update update) {

		String userId;
		if (update.hasMessage()) {
			userId = update.getMessage().getFrom().getId().toString();
		} else {
			userId = update.getCallbackQuery().getFrom().getId().toString();
		}
		var buttons = keyboard.create();

		cache.saveState(Long.parseLong(userId), State.CARD_OR_CASH);

		return callback.create(userId, TEXT_MESSAGE, buttons);
	}
}
