package dev.yudin.yourpersonalfinancesbot.handlers.try_again;

import dev.yudin.yourpersonalfinancesbot.cache.ExpenseCache;
import dev.yudin.yourpersonalfinancesbot.cache.StatesCache;
import dev.yudin.yourpersonalfinancesbot.callbacks.Callback;
import dev.yudin.yourpersonalfinancesbot.dao.impl.ExpenseDAOImpl;
import dev.yudin.yourpersonalfinancesbot.enums.State;
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
@Component("TryAgainState")
public class TryAgainHandler implements Handler {

	public static final String TEXT_MESSAGE = "Expense saved! \nDo ya wanna continue?";

	private Keyboard keyboard;
	private Callback callback;
	private ExpenseCache expenseCache;
	private StatesCache statesCache;
	private ExpenseDAOImpl expenseDAO;

	@Autowired
	public TryAgainHandler(@Qualifier("TryAgainKeyboard") Keyboard keyboard,
						   @Qualifier("TryAgainCallback") Callback callback,
						   ExpenseCache expenseCache,
						   StatesCache statesCache, ExpenseDAOImpl expenseDAO) {
		this.keyboard = keyboard;
		this.callback = callback;
		this.expenseCache = expenseCache;
		this.statesCache = statesCache;
		this.expenseDAO = expenseDAO;
	}

	@Override
	public SendMessage handle(Update update) {

		String sum;
		long userId = 0;
		if (update.hasMessage()) {
			userId = update.getMessage().getFrom().getId();
			sum = update.getMessage().getText();
		} else {
			userId = update.getCallbackQuery().getFrom().getId();
			sum = update.getCallbackQuery().getData();
		}
//		saveExpenseSum(sum, userId);

		statesCache.saveState(userId, State.TRY_AGAIN);

		InlineKeyboardMarkup buttons = keyboard.create();

		return callback.create(String.valueOf(userId), TEXT_MESSAGE, buttons);
	}

	private void saveExpenseSum(String sum, long userId) {
		var expense = expenseCache.getExpense(userId);
		expense.setSum(Integer.parseInt(sum));

		log.info("expense state: " + expense);
		expenseCache.saveExpense(userId, expense);
		//Save into database STUB
		expenseDAO.save(expense);

		expenseCache.cleanCache(userId);
		log.info("ExpenseCache: " + expenseCache.getExpense(userId));
	}
}
