package dev.yudin.YourPersonalFinancesBot.states_handler;

import dev.yudin.YourPersonalFinancesBot.enums.State;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Log4j
@Component
public class StateHandlerChooser {

	private Handler mainMenu;
	private Handler chooseExpenseCategory;
	private Handler cardCash;
	private Handler enterSum;
	private Handler expenseAccepted;
	private Handler tryAgain;
	private Handler enterMySum;
	private Handler noAnswer;

	private final Map<State, Handler> statesHandlers = new EnumMap<>(State.class);

	@Autowired
	public StateHandlerChooser(@Qualifier("MainMenuState") Handler mainMenu,
							   @Qualifier("ChooseExpenseCategoryState") Handler chooseExpenseCategory,
							   @Qualifier("CardCashState") Handler cardCash,
							   @Qualifier("EnterSumState") Handler enterSum,
							   @Qualifier("ExpenseAcceptedHandler") Handler expenseAccepted,
							   @Qualifier("TryAgainState") Handler tryAgain,
							   @Qualifier("MySumState") Handler enterMySum,
							   @Qualifier("NoAnswerState") Handler noAnswer) {
		this.mainMenu = mainMenu;
		this.chooseExpenseCategory = chooseExpenseCategory;
		this.cardCash = cardCash;
		this.enterSum = enterSum;
		this.expenseAccepted = expenseAccepted;
		this.tryAgain = tryAgain;
		this.enterMySum = enterMySum;
		this.noAnswer = noAnswer;
	}

//	private void initHandlers() {
//		statesHandlers.put(State.SHOW_MAIN_MENU, mainMenu);
//		statesHandlers.put(State.ADD_EXPENSE, chooseExpenseCategory);
//		statesHandlers.put(State.CHOOSE_EXPENSE_CATEGORY, cardCash);
//		statesHandlers.put(State.ENTER_CARD_OR_CASH, enterSum);
//		statesHandlers.put(State.ENTER_SUM, tryAgain);
//		statesHandlers.put(State.MY_SUM, enterMySum);
//		statesHandlers.put(State.TRY_AGAIN, mainMenu);
//		statesHandlers.put(State.NO_ANSWER, noAnswer);
//		statesHandlers.put(State.YES_ANSWER, mainMenu);
//	}

	private void initHandlers() {
		statesHandlers.put(State.MAIN_MENU, mainMenu);

		statesHandlers.put(State.ADD_EXPENSE, chooseExpenseCategory);
		statesHandlers.put(State.CARD_OR_CASH, cardCash);
		statesHandlers.put(State.EXPENSE_SUM, enterSum);
		statesHandlers.put(State.EXPENSE_MY_SUM, enterMySum);
		statesHandlers.put(State.EXPENSE_MY_SUM_START, enterMySum);
		statesHandlers.put(State.EXPENSE_MY_SUM_FINISH, tryAgain);

		statesHandlers.put(State.TRY_AGAIN, tryAgain);
		statesHandlers.put(State.YES_ANSWER, mainMenu);
		statesHandlers.put(State.NO_ANSWER, noAnswer);
	}

	public Handler defineFor(State state) {
		if (statesHandlers.isEmpty()) {
			initHandlers();
		}
		Handler handler = getFor(state);

		String handlerName = handler.getClass().getSimpleName();
		log.info(state + " -> " + handlerName);
		log.info("---");
		return handler;
	}

	private Handler getFor(State state) {
		return statesHandlers.getOrDefault(state, mainMenu);
	}
}
