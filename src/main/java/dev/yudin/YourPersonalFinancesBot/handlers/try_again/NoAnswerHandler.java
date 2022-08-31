package dev.yudin.YourPersonalFinancesBot.handlers.try_again;

import dev.yudin.YourPersonalFinancesBot.cache.StatesCache;
import dev.yudin.YourPersonalFinancesBot.callbacks.try_again.NoAnswerCallback;
import dev.yudin.YourPersonalFinancesBot.enums.State;
import dev.yudin.YourPersonalFinancesBot.states_handler.Handler;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Log4j
@Component("NoAnswerState")
public class NoAnswerHandler implements Handler {

	public static final String TEXT_MESSAGE = "See ya later alligator!";
	private NoAnswerCallback callback;
	private StatesCache statesCache;

	@Autowired
	public NoAnswerHandler(NoAnswerCallback callback, StatesCache statesCache) {
		this.callback = callback;
		this.statesCache = statesCache;
	}

	@Override
	public SendMessage apply(Update update) {
		long userId = update.getCallbackQuery().getFrom().getId();

		statesCache.saveState(userId, State.MAIN_MENU);

		return callback.createCallback(String.valueOf(userId), TEXT_MESSAGE);
	}
}
