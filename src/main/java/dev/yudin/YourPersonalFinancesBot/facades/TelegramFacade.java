package dev.yudin.YourPersonalFinancesBot.facades;

import dev.yudin.YourPersonalFinancesBot.cache.ExpenseCache;
import dev.yudin.YourPersonalFinancesBot.cache.StatesCache;
import dev.yudin.YourPersonalFinancesBot.dao.impl.ExpenseDAOImpl;
import dev.yudin.YourPersonalFinancesBot.enums.State;
import dev.yudin.YourPersonalFinancesBot.states_handler.Handler;
import dev.yudin.YourPersonalFinancesBot.states_handler.StateHandler;
import dev.yudin.YourPersonalFinancesBot.states_handler.StateHandlerChooser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TelegramFacade implements Facade {

	private static final Logger log = LogManager.getLogger();

	private StatesCache statesCache;
	private StateHandlerChooser handlerChooser;
	private ExpenseCache expenseCache;
	private ExpenseDAOImpl expenseDAO;
	private StateHandler stateHandler;

	@Autowired
	public TelegramFacade(StatesCache statesCache,
						  StateHandlerChooser handlerChooser,
						  ExpenseCache expenseCache,
						  ExpenseDAOImpl expenseDAO, StateHandler stateHandler) {
		this.statesCache = statesCache;
		this.handlerChooser = handlerChooser;
		this.expenseCache = expenseCache;
		this.expenseDAO = expenseDAO;
		this.stateHandler = stateHandler;
	}

	//Front controller
	@Override
	public SendMessage handle(Update update) {

		long userId;
		SendMessage replyMessage = null;
		if (update.hasMessage()) {
			userId = update.getMessage().getFrom().getId();

			State userState = statesCache.defineStateBy(userId);
			if (userState == State.EXPENSE_MY_SUM) {
				String sum = update.getMessage().getText();

//				saveExpenseSum(sum, userId);
				userState = State.EXPENSE_ACCEPTED;
			}
			Handler handler = handlerChooser.defineFor(userState);

			replyMessage = handler.apply(update);
		}
		if (update.hasCallbackQuery()) {
			CallbackQuery callbackQuery = update.getCallbackQuery();

			String buttonData = callbackQuery.getData();
			log.info("callback date: " + buttonData);
			userId = callbackQuery.getFrom().getId();

			State state = State.fromString(buttonData);
			if (state == State.UNKNOWN) {
				state = statesCache.defineStateBy(userId);
			}
			if (state == State.EXPENSE_SUM) {
				state = stateHandler.getState(buttonData);
			}
			if (state == State.TRY_AGAIN) {
				state = stateHandler.getState(buttonData);
			}
			if (state == State.YES_ANSWER) {
				statesCache.saveState(userId, State.MAIN_MENU);
			}
			Handler handler = handlerChooser.defineFor(state);

			replyMessage = handler.apply(update);
		}
		return replyMessage;
	}

	private Date getDate(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

		Date result = null;
		try {
			result = dateFormat.parse(date);

			return result;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
}
