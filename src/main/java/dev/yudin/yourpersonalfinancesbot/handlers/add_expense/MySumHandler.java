package dev.yudin.yourpersonalfinancesbot.handlers.add_expense;

import dev.yudin.yourpersonalfinancesbot.cache.ExpenseCache;
import dev.yudin.yourpersonalfinancesbot.cache.StatesCache;
import dev.yudin.yourpersonalfinancesbot.callbacks.expense.MySumCallback;
import dev.yudin.yourpersonalfinancesbot.enums.State;
import dev.yudin.yourpersonalfinancesbot.states_handler.Handler;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Log4j
@Component("MySumState")
public class MySumHandler implements Handler {

	public static final String TEXT_MESSAGE = "Enter sum";

	private MySumCallback callback;
	private ExpenseCache expenseCache;
	private StatesCache statesCache;

	@Autowired
	public MySumHandler(MySumCallback callback,
						ExpenseCache expenseCache,
						StatesCache statesCache) {
		this.callback = callback;
		this.expenseCache = expenseCache;
		this.statesCache = statesCache;
	}

	@Override
	public SendMessage handle(Update update) {
		long userId = update.getCallbackQuery().getFrom().getId();

		statesCache.saveState(userId, State.EXPENSE_MY_SUM);

		return callback.createCallback(String.valueOf(userId), TEXT_MESSAGE);
	}
}
