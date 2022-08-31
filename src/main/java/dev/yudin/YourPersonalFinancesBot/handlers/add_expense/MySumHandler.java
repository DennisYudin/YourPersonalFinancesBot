package dev.yudin.YourPersonalFinancesBot.handlers.add_expense;

import dev.yudin.YourPersonalFinancesBot.cache.ExpenseCache;
import dev.yudin.YourPersonalFinancesBot.cache.StatesCache;
import dev.yudin.YourPersonalFinancesBot.callbacks.expense.MySumCallback;
import dev.yudin.YourPersonalFinancesBot.enums.State;
import dev.yudin.YourPersonalFinancesBot.states_handler.Handler;
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
	public SendMessage apply(Update update) {
		long userId = update.getCallbackQuery().getFrom().getId();

		statesCache.saveState(userId, State.EXPENSE_MY_SUM);

		return callback.createCallback(String.valueOf(userId), TEXT_MESSAGE);
	}
}
