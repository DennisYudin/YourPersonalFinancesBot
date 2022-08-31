package dev.yudin.YourPersonalFinancesBot.handlers.add_expense;

import dev.yudin.YourPersonalFinancesBot.cache.StatesCache;
import dev.yudin.YourPersonalFinancesBot.callbacks.expense.ExpenseAcceptedCallback;
import dev.yudin.YourPersonalFinancesBot.enums.State;
import dev.yudin.YourPersonalFinancesBot.states_handler.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component("ExpenseAcceptedHandler")
public class ExpenseAcceptedHandler implements Handler {

	public static final String TEXT_MESSAGE = "Expense saved!";
	private ExpenseAcceptedCallback callback;
	private StatesCache statesCache;

	@Autowired
	public ExpenseAcceptedHandler(ExpenseAcceptedCallback callback,
								  StatesCache statesCache) {
		this.callback = callback;
		this.statesCache = statesCache;
	}

	@Override
	public SendMessage apply(Update update) {
		long userId = update.getMessage().getFrom().getId();

		statesCache.saveState(userId, State.TRY_AGAIN);

		return callback.createCallback(String.valueOf(userId), TEXT_MESSAGE);
	}
}
