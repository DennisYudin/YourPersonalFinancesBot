package dev.yudin.yourpersonalfinancesbot.states_handler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.yudin.yourpersonalfinancesbot.enums.State;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StateHandlerChooserTest {

	@Autowired
	private StateHandlerChooser stateHandlerChooser;

	@Test
	void defineFor_ShouldReturnMainMenuHandler_WhenInputIsShowMainMenuState() {

		Handler handler = stateHandlerChooser.findHandlerFor(State.MAIN_MENU);
		String actualHandler = handler.getClass().getSimpleName();
		String expectedHandler = "MainMenuHandler";

		assertEquals(expectedHandler, actualHandler);
	}

	@Test
	void defineFor_ShouldReturnChooseCategoryExpenseHandler_WhenInputIsAddExpenseState() {

		Handler handler = stateHandlerChooser.findHandlerFor(State.ADD_EXPENSE);
		String actualHandler = handler.getClass().getSimpleName();
		String expectedHandler = "ChooseExpenseCategoryHandler";

		assertEquals(expectedHandler, actualHandler);
	}

	@Test
	void defineFor_ShouldReturnCardCashHandler_WhenInputIsCardCashState() {

		Handler handler = stateHandlerChooser.findHandlerFor(State.CARD_OR_CASH);
		String actualHandler = handler.getClass().getSimpleName();
		String expectedHandler = "CardCashHandler";

		assertEquals(expectedHandler, actualHandler);
	}
}
