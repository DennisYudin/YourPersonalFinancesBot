package dev.yudin.yourpersonalfinancesbot.states_handler;

import dev.yudin.yourpersonalfinancesbot.enums.State;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;
import javax.annotation.PostConstruct;

@Log4j
@Component
public class StateHandlerChooser {
	private Handler mainMenuHandler;
	private Handler chooseExpenseCategoryHandler;
	private Handler cardCashHandler;
	private Handler enterSumHandler;
	private Handler expenseAccepted;
	private Handler tryAgainHandler;
	private Handler enterMySumHandler;
	private Handler noAnswerHandler;

	private final Map<State, Handler> statesHandlers = new EnumMap<>(State.class);

	@Autowired
	public StateHandlerChooser(@Qualifier("MainMenuState") Handler mainMenuHandler,
							   @Qualifier("ChooseExpenseCategoryState") Handler chooseExpenseCategoryHandler,
							   @Qualifier("CardCashState") Handler cardCashHandler,
							   @Qualifier("EnterSumState") Handler enterSumHandler,
							   @Qualifier("ExpenseAcceptedHandler") Handler expenseAccepted,
							   @Qualifier("TryAgainState") Handler tryAgainHandler,
							   @Qualifier("MySumState") Handler enterMySumHandler,
							   @Qualifier("NoAnswerState") Handler noAnswerHandler) {
		this.mainMenuHandler = mainMenuHandler;
		this.chooseExpenseCategoryHandler = chooseExpenseCategoryHandler;
		this.cardCashHandler = cardCashHandler;
		this.enterSumHandler = enterSumHandler;
		this.expenseAccepted = expenseAccepted;
		this.tryAgainHandler = tryAgainHandler;
		this.enterMySumHandler = enterMySumHandler;
		this.noAnswerHandler = noAnswerHandler;
	}

	@PostConstruct
	private void initHandlers() {
		log.info("Start init handlers...");
		statesHandlers.put(State.MAIN_MENU, mainMenuHandler);

		statesHandlers.put(State.ADD_EXPENSE, chooseExpenseCategoryHandler);
		statesHandlers.put(State.CARD_OR_CASH, cardCashHandler);
		statesHandlers.put(State.EXPENSE_SUM, enterSumHandler);
		statesHandlers.put(State.EXPENSE_MY_SUM, enterMySumHandler);
		statesHandlers.put(State.EXPENSE_MY_SUM_START, enterMySumHandler);
		statesHandlers.put(State.EXPENSE_MY_SUM_FINISH, tryAgainHandler);

		statesHandlers.put(State.TRY_AGAIN, tryAgainHandler);
		statesHandlers.put(State.YES_ANSWER, mainMenuHandler);
		statesHandlers.put(State.NO_ANSWER, noAnswerHandler);

		log.info("All handlers init succeeded");
	}

	public Handler findHandlerFor(State state) {

		Handler handler = statesHandlers.getOrDefault(state, mainMenuHandler);

//		String handlerName = handler.getClass().getSimpleName();
//		log.info(state + " -> " + handlerName);
//		log.info("---------");
		return handler;
	}
}
