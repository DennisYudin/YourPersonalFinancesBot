package dev.yudin.yourpersonalfinancesbot.services;

import dev.yudin.yourpersonalfinancesbot.cache.StatesCache;
import dev.yudin.yourpersonalfinancesbot.entities.Expense;
import dev.yudin.yourpersonalfinancesbot.enums.State;
import dev.yudin.yourpersonalfinancesbot.states_handler.StateHandlerChooser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

@Component
public class CallbackHandler {
	private static final Logger log = LogManager.getLogger();

	private StatesCache statesCache;
	private StateHandlerChooser stateHandlerChooser;

	List<String> expenseCategories = new ArrayList<>();
	{
		expenseCategories.add("Medical/Healthcare");
		expenseCategories.add("Food");
		expenseCategories.add("Housing");
		expenseCategories.add("Transportation");
		expenseCategories.add("Personal");
		expenseCategories.add("Utilities");
		expenseCategories.add("Clothing");
		expenseCategories.add("Education");
		expenseCategories.add("Entertainment");
		expenseCategories.add("Other");
	}

	@Autowired
	public CallbackHandler(StatesCache statesCache, StateHandlerChooser stateHandlerChooser) {
		this.statesCache = statesCache;
		this.stateHandlerChooser = stateHandlerChooser;
	}

	public SendMessage handle(Update update) {
		log.info("Call method handle()");

		CallbackQuery callbackQuery = update.getCallbackQuery();
		logInputParams(callbackQuery);

		State state = getState(update.getMessage());

		SendMessage reply = process(callbackQuery);

		return reply;
	}

	private State getState(Message message) {
		long userId = message.getFrom().getId();
		State state = statesCache.defineUserStateBy(userId);
		logState(message, state);
		return state;
	}

	private void logState(Message message, State state) {
		log.info("params: userName: {}, state: {}",
				message.getFrom().getUserName(),
				state);
	}

	private SendMessage process(CallbackQuery buttonQuery) {
		String chatId = buttonQuery.getMessage().getChatId().toString();
		long userId = buttonQuery.getFrom().getId();

		Integer date = buttonQuery.getMessage().getDate();
		java.sql.Date sqlDate = new java.sql.Date(date * 1000L);

		String buttonDate = buttonQuery.getData();

		Expense expenseDTO = new Expense();
		expenseDTO.setUserId(userId);
//		expenseDTO.setDate(sqlDate);
		log.info("expenseDTO: " + expenseDTO);

		SendMessage callBackAnswer = null;
		if (buttonDate.equals("AddExpense")) {
			statesCache.saveState(userId, State.ADD_EXPENSE);

//			callBackAnswer = stateHandler.handle(statesCache.defineStateBy(userId), buttonQuery.getMessage());
			log.info("callBackAnswer: " + callBackAnswer);
		} else if (expenseCategories.contains(buttonDate)) {
//			statesCache.saveState(userId, BotState.ENTER_CARD_OR_CASH);

			expenseDTO.setExpenseName(buttonDate);
			log.info("expenseDTO: " + expenseDTO);

//			callBackAnswer = stateHandler.handle(statesCache.defineStateBy(userId), buttonQuery.getMessage());
		} else if (buttonDate.equals("Card") || buttonDate.equals("Cash")) {
//			statesCache.saveState(userId, BotState.ENTER_SUM);

			expenseDTO.setExpenseType(buttonDate);
			log.info("expenseDTO: " + expenseDTO);

			callBackAnswer = new SendMessage(chatId, "Enter sum");
		}
		return callBackAnswer;
	}

	private void logInputParams(CallbackQuery callbackQuery) {
		log.info("New callbackQuery from User: {}, userId: {}, with data: {}",
				callbackQuery.getFrom().getUserName(),
				callbackQuery.getFrom().getId(),
				callbackQuery.getData());
	}
}
