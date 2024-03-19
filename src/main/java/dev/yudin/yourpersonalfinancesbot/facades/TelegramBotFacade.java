package dev.yudin.yourpersonalfinancesbot.facades;

import dev.yudin.yourpersonalfinancesbot.cache.StatesCache;
import dev.yudin.yourpersonalfinancesbot.dao.impl.ExpenseDAOImpl;
import dev.yudin.yourpersonalfinancesbot.enums.State;
import dev.yudin.yourpersonalfinancesbot.states_handler.Handler;
import dev.yudin.yourpersonalfinancesbot.states_handler.StateHandler;
import dev.yudin.yourpersonalfinancesbot.states_handler.StateHandlerChooser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class TelegramBotFacade implements Facade {
	private static final Logger log = LogManager.getLogger();

	private StatesCache statesCache;
	private StateHandlerChooser handlerChooser;
	private ExpenseDAOImpl expenseDAO;
	private StateHandler stateHandler;

	@Autowired
	public TelegramBotFacade(StatesCache statesCache,
							 StateHandlerChooser handlerChooser,
							 ExpenseDAOImpl expenseDAO, StateHandler stateHandler) {
		this.statesCache = statesCache;
		this.handlerChooser = handlerChooser;
		this.expenseDAO = expenseDAO;
		this.stateHandler = stateHandler;
	}

	//Front controller
	@Override
	public SendMessage handle(Update update) {

		SendMessage response;
		if (update.hasMessage()) {
			long userId = update.getMessage().getFrom().getId();
			log.info("Got message from user: {}", userId);
			State currentUserState = statesCache.defineUserStateBy(userId);
			log.info("USER: {} current STATE: {}", userId, currentUserState);

			if (currentUserState == State.EXPENSE_MY_SUM) {
				currentUserState = State.EXPENSE_MY_SUM_FINISH;
			}
			Handler handler = handlerChooser.findHandlerFor(currentUserState);
			log.info("Found a handler: {} for user state: {}", handler.getClass().getSimpleName(), currentUserState);

			response = handler.handle(update);
		} else {
			CallbackQuery callbackQuery = update.getCallbackQuery();
			long userId = callbackQuery.getFrom().getId();
			log.info("Got CALLBACK from user: {}", userId);

			String userChoice = callbackQuery.getData();
			log.info("USER: {} choice: {}",userId, userChoice);

			State desiredState = State.fromString(userChoice);
			if (desiredState == State.UNKNOWN) {
				desiredState = statesCache.defineUserStateBy(userId);
			}
			if (desiredState == State.EXPENSE_SUM) {
				desiredState = stateHandler.getState(userChoice);
			}
			if (desiredState == State.TRY_AGAIN) {
				desiredState = stateHandler.getState(userChoice);
			}
			if (desiredState == State.YES_ANSWER) {
				statesCache.saveState(userId, State.MAIN_MENU);
			}
			Handler handler = handlerChooser.findHandlerFor(desiredState);

			response = handler.handle(update);
		}
		return response;
	}
}
