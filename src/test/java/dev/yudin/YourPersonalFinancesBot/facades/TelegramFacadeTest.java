package dev.yudin.YourPersonalFinancesBot.facades;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.yudin.YourPersonalFinancesBot.cache.Cache;
import dev.yudin.YourPersonalFinancesBot.enums.State;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

@SpringBootTest
class TelegramFacadeTest {

	@Autowired
	private TelegramFacade facade;
	@Autowired
	private Cache statesCache;

	@Test
	void handle_ShouldCheckClassicScenarioWithReturnToMainDialogue() {

		//Main menu Dialogue
		Update initUpdate = initUpdate("start");

		SendMessage mainMenuDialogue = facade.handle(initUpdate);

		assertTrue(mainMenuDialogue.getReplyMarkup().toString().contains("text=Add expense"));
		assertTrue(mainMenuDialogue.getReplyMarkup().toString().contains("text=Add income"));
		assertTrue(mainMenuDialogue.getReplyMarkup().toString().contains("text=Show expenses"));
		assertTrue(mainMenuDialogue.getReplyMarkup().toString().contains("text=Show statistic"));

		State userState = statesCache.defineStateBy(201465862L);
		assertEquals(State.MAIN_MENU, userState);

		//Choose expense category Dialogue
		Update pushedButton = getUpdateWithCallback("Add expense");

		SendMessage chooseExpenseDialogue = facade.handle(pushedButton);

		assertTrue(chooseExpenseDialogue.getReplyMarkup().toString().contains("text=Food"));
		assertTrue(chooseExpenseDialogue.getReplyMarkup().toString().contains("text=Personal"));
		assertTrue(chooseExpenseDialogue.getReplyMarkup().toString().contains("text=Other"));
		assertTrue(chooseExpenseDialogue.getReplyMarkup().toString().contains("text=Clothing"));

		State currentState = statesCache.defineStateBy(201465862L);
		assertEquals(State.CARD_OR_CASH, currentState);

		//Choose card or cash Dialogue
		Update pushedButton2 = getUpdateWithCallback("Food");

		SendMessage cardOrCashDialogue = facade.handle(pushedButton2);

		assertTrue(cardOrCashDialogue.getReplyMarkup().toString().contains("text=Card"));
		assertTrue(cardOrCashDialogue.getReplyMarkup().toString().contains("text=Cash"));

		State currentState2 = statesCache.defineStateBy(201465862L);
		assertEquals(State.EXPENSE_SUM, currentState2);

		//Choose or enter sum Dialogue
		Update pushedButton3 = getUpdateWithCallback("Card");

		SendMessage enterSumDialogue = facade.handle(pushedButton3);

		assertTrue(enterSumDialogue.getReplyMarkup().toString().contains("text=100"));
		assertTrue(enterSumDialogue.getReplyMarkup().toString().contains("text=300"));
		assertTrue(enterSumDialogue.getReplyMarkup().toString().contains("text=500"));
		assertTrue(enterSumDialogue.getReplyMarkup().toString().contains("text=My sum"));

		State currentState3 = statesCache.defineStateBy(201465862L);
		assertEquals(State.EXPENSE_SUM, currentState3);

		//Try again Dialogue
		Update pushedButton4 = getUpdateWithCallback("100");

		SendMessage tryAgainDialogue = facade.handle(pushedButton4);

		assertTrue(tryAgainDialogue.getReplyMarkup().toString().contains("text=Yes"));
		assertTrue(tryAgainDialogue.getReplyMarkup().toString().contains("text=No"));

		State currentState4 = statesCache.defineStateBy(201465862L);
		assertEquals(State.TRY_AGAIN, currentState4);

		//-----
		Update pushedButton5 = getUpdateWithCallback("Yes");
		//Main menu dialogue
		SendMessage mainMenuDialogueRepeat = facade.handle(pushedButton5);

		assertTrue(mainMenuDialogueRepeat.getReplyMarkup().toString().contains("text=Add expense"));
		assertTrue(mainMenuDialogueRepeat.getReplyMarkup().toString().contains("text=Add income"));
		assertTrue(mainMenuDialogueRepeat.getReplyMarkup().toString().contains("text=Show expenses"));
		assertTrue(mainMenuDialogueRepeat.getReplyMarkup().toString().contains("text=Show statistic"));

		State currentState5 = statesCache.defineStateBy(201465862L);
		assertEquals(State.MAIN_MENU, currentState5);
	}

	@Test
	void handle_ShouldCheckClassicScenarioAndGoodBuyDialogue() {

		//Main menu Dialogue
		Update initUpdate = initUpdate("start");

		SendMessage mainMenuDialogue = facade.handle(initUpdate);

		assertTrue(mainMenuDialogue.getReplyMarkup().toString().contains("text=Add expense"));
		assertTrue(mainMenuDialogue.getReplyMarkup().toString().contains("text=Add income"));
		assertTrue(mainMenuDialogue.getReplyMarkup().toString().contains("text=Show expenses"));
		assertTrue(mainMenuDialogue.getReplyMarkup().toString().contains("text=Show statistic"));

		State userState = statesCache.defineStateBy(201465862L);
		assertEquals(State.MAIN_MENU, userState);

		//Choose expense category Dialogue
		Update pushedButton = getUpdateWithCallback("Add expense");

		SendMessage chooseExpenseDialogue = facade.handle(pushedButton);

		assertTrue(chooseExpenseDialogue.getReplyMarkup().toString().contains("text=Food"));
		assertTrue(chooseExpenseDialogue.getReplyMarkup().toString().contains("text=Personal"));
		assertTrue(chooseExpenseDialogue.getReplyMarkup().toString().contains("text=Other"));
		assertTrue(chooseExpenseDialogue.getReplyMarkup().toString().contains("text=Clothing"));

		State currentState = statesCache.defineStateBy(201465862L);
		assertEquals(State.CARD_OR_CASH, currentState);

		//Choose card or cash Dialogue
		Update pushedButton2 = getUpdateWithCallback("Food");

		SendMessage cardOrCashDialogue = facade.handle(pushedButton2);

		assertTrue(cardOrCashDialogue.getReplyMarkup().toString().contains("text=Card"));
		assertTrue(cardOrCashDialogue.getReplyMarkup().toString().contains("text=Cash"));

		State currentState2 = statesCache.defineStateBy(201465862L);
		assertEquals(State.EXPENSE_SUM, currentState2);

		//Choose or enter sum Dialogue
		Update pushedButton3 = getUpdateWithCallback("Card");

		SendMessage enterSumDialogue = facade.handle(pushedButton3);

		assertTrue(enterSumDialogue.getReplyMarkup().toString().contains("text=100"));
		assertTrue(enterSumDialogue.getReplyMarkup().toString().contains("text=300"));
		assertTrue(enterSumDialogue.getReplyMarkup().toString().contains("text=500"));
		assertTrue(enterSumDialogue.getReplyMarkup().toString().contains("text=My sum"));

		State currentState3 = statesCache.defineStateBy(201465862L);
		assertEquals(State.EXPENSE_SUM, currentState3);

		//Try again Dialogue
		Update pushedButton4 = getUpdateWithCallback("100");

		SendMessage tryAgainDialogue = facade.handle(pushedButton4);

		assertTrue(tryAgainDialogue.getReplyMarkup().toString().contains("text=Yes"));
		assertTrue(tryAgainDialogue.getReplyMarkup().toString().contains("text=No"));

		State currentState4 = statesCache.defineStateBy(201465862L);
		assertEquals(State.TRY_AGAIN, currentState4);

		//-----
		Update pushedButton5 = getUpdateWithCallback("No");
		//Main menu dialogue
		SendMessage finishMessage = facade.handle(pushedButton5);

		String actualText = finishMessage.getText();
		String expected = "See ya later alligator!";

		assertEquals(expected, actualText);

		State currentState5 = statesCache.defineStateBy(201465862L);
		assertEquals(State.MAIN_MENU, currentState5);
	}

	@Test
	void handle_ShouldCheckScenarioWithMySumButtonAndGoodBuyDialogue() {

		//Main menu Dialogue
		Update initUpdate = initUpdate("start");

		SendMessage mainMenuDialogue = facade.handle(initUpdate);

		assertTrue(mainMenuDialogue.getReplyMarkup().toString().contains("text=Add expense"));
		assertTrue(mainMenuDialogue.getReplyMarkup().toString().contains("text=Add income"));
		assertTrue(mainMenuDialogue.getReplyMarkup().toString().contains("text=Show expenses"));
		assertTrue(mainMenuDialogue.getReplyMarkup().toString().contains("text=Show statistic"));

		State userState = statesCache.defineStateBy(201465862L);
		assertEquals(State.MAIN_MENU, userState);

		//Choose expense category Dialogue
		Update pushedButton = getUpdateWithCallback("Add expense");

		SendMessage chooseExpenseDialogue = facade.handle(pushedButton);

		assertTrue(chooseExpenseDialogue.getReplyMarkup().toString().contains("text=Food"));
		assertTrue(chooseExpenseDialogue.getReplyMarkup().toString().contains("text=Personal"));
		assertTrue(chooseExpenseDialogue.getReplyMarkup().toString().contains("text=Other"));
		assertTrue(chooseExpenseDialogue.getReplyMarkup().toString().contains("text=Clothing"));

		State currentState = statesCache.defineStateBy(201465862L);
		assertEquals(State.CARD_OR_CASH, currentState);

		//Choose card or cash Dialogue
		Update pushedButton2 = getUpdateWithCallback("Food");

		SendMessage cardOrCashDialogue = facade.handle(pushedButton2);

		assertTrue(cardOrCashDialogue.getReplyMarkup().toString().contains("text=Card"));
		assertTrue(cardOrCashDialogue.getReplyMarkup().toString().contains("text=Cash"));

		State currentState2 = statesCache.defineStateBy(201465862L);
		assertEquals(State.EXPENSE_SUM, currentState2);

		//Choose or enter sum Dialogue
		Update pushedButton3 = getUpdateWithCallback("Card");

		SendMessage enterSumDialogue = facade.handle(pushedButton3);

		assertTrue(enterSumDialogue.getReplyMarkup().toString().contains("text=100"));
		assertTrue(enterSumDialogue.getReplyMarkup().toString().contains("text=300"));
		assertTrue(enterSumDialogue.getReplyMarkup().toString().contains("text=500"));
		assertTrue(enterSumDialogue.getReplyMarkup().toString().contains("text=My sum"));

		State currentState3 = statesCache.defineStateBy(201465862L);
		assertEquals(State.EXPENSE_SUM, currentState3);

		//Enter sum
		Update pushedButton4 = getUpdateWithCallback("My sum");

		SendMessage inputSumDialogue = facade.handle(pushedButton4);

		String actual = inputSumDialogue.getText();
		String expected = "Enter sum";

		assertEquals(expected, actual);

		State currentState4 = statesCache.defineStateBy(201465862L);
		assertEquals(State.EXPENSE_MY_SUM, currentState4);

		//-------------
		Update updateWithMySum = initUpdate("1500");

		SendMessage mySum = facade.handle(updateWithMySum);

		String actualText = mySum.getText();
		String expectedText = "Expense saved!";

		assertEquals(expectedText, actualText);
		State currentState5 = statesCache.defineStateBy(201465862L);
		assertEquals(State.TRY_AGAIN, currentState5);

		//-----
		Update pushedButton5 = getUpdateWithCallback("No");
		//Goodbuy dialogue
		SendMessage goodBuyMessage = facade.handle(pushedButton5);

		String actualGoodBuy = goodBuyMessage.getText();
		String expectedGoodBuy = "See ya later alligator!";

		assertEquals(expectedGoodBuy, actualGoodBuy);

		State currentState6 = statesCache.defineStateBy(201465862L);
		assertEquals(State.MAIN_MENU, currentState6);
	}

	@Test
	void handle_ShouldCheckScenarioWithMySumButtonAndMainMenuDialogue() {

		//Main menu Dialogue
		Update initUpdate = initUpdate("start");

		SendMessage mainMenuDialogue = facade.handle(initUpdate);

		assertTrue(mainMenuDialogue.getReplyMarkup().toString().contains("text=Add expense"));
		assertTrue(mainMenuDialogue.getReplyMarkup().toString().contains("text=Add income"));
		assertTrue(mainMenuDialogue.getReplyMarkup().toString().contains("text=Show expenses"));
		assertTrue(mainMenuDialogue.getReplyMarkup().toString().contains("text=Show statistic"));

		State userState = statesCache.defineStateBy(201465862L);
		assertEquals(State.MAIN_MENU, userState);

		//Choose expense category Dialogue
		Update pushedButton = getUpdateWithCallback("Add expense");

		SendMessage chooseExpenseDialogue = facade.handle(pushedButton);

		assertTrue(chooseExpenseDialogue.getReplyMarkup().toString().contains("text=Food"));
		assertTrue(chooseExpenseDialogue.getReplyMarkup().toString().contains("text=Personal"));
		assertTrue(chooseExpenseDialogue.getReplyMarkup().toString().contains("text=Other"));
		assertTrue(chooseExpenseDialogue.getReplyMarkup().toString().contains("text=Clothing"));

		State currentState = statesCache.defineStateBy(201465862L);
		assertEquals(State.CARD_OR_CASH, currentState);

		//Choose card or cash Dialogue
		Update pushedButton2 = getUpdateWithCallback("Food");

		SendMessage cardOrCashDialogue = facade.handle(pushedButton2);

		assertTrue(cardOrCashDialogue.getReplyMarkup().toString().contains("text=Card"));
		assertTrue(cardOrCashDialogue.getReplyMarkup().toString().contains("text=Cash"));

		State currentState2 = statesCache.defineStateBy(201465862L);
		assertEquals(State.EXPENSE_SUM, currentState2);

		//Choose or enter sum Dialogue
		Update pushedButton3 = getUpdateWithCallback("Card");

		SendMessage enterSumDialogue = facade.handle(pushedButton3);

		assertTrue(enterSumDialogue.getReplyMarkup().toString().contains("text=100"));
		assertTrue(enterSumDialogue.getReplyMarkup().toString().contains("text=300"));
		assertTrue(enterSumDialogue.getReplyMarkup().toString().contains("text=500"));
		assertTrue(enterSumDialogue.getReplyMarkup().toString().contains("text=My sum"));

		State currentState3 = statesCache.defineStateBy(201465862L);
		assertEquals(State.EXPENSE_SUM, currentState3);

		//Try again Dialogue
		Update pushedButton4 = getUpdateWithCallback("My sum");

		SendMessage inputSumDialogue = facade.handle(pushedButton4);

		String actual = inputSumDialogue.getText();
		String expected = "Enter sum";

		assertEquals(expected, actual);

		State currentState4 = statesCache.defineStateBy(201465862L);
		assertEquals(State.TRY_AGAIN, currentState4);

		Update pushedButton5 = getUpdateWithCallback("Yes");
		//Main menu dialogue
		SendMessage mainMenuDialogueRepeat = facade.handle(pushedButton5);

		assertTrue(mainMenuDialogueRepeat.getReplyMarkup().toString().contains("text=Add expense"));
		assertTrue(mainMenuDialogueRepeat.getReplyMarkup().toString().contains("text=Add income"));
		assertTrue(mainMenuDialogueRepeat.getReplyMarkup().toString().contains("text=Show expenses"));
		assertTrue(mainMenuDialogueRepeat.getReplyMarkup().toString().contains("text=Show statistic"));

		State currentState5 = statesCache.defineStateBy(201465862L);
		assertEquals(State.MAIN_MENU, currentState5);
	}

	private Update initUpdate(String input) {
		Update update = new Update();

		User user = new User();
		user.setId(201465862L);
		user.setUserName("Dennis_Yudin");
		user.setFirstName("Денис");
		user.setLastName("Юдин");

		Message message = new Message();
		message.setMessageId(731);
		message.setText(input);
		message.setFrom(user);
		message.setDate(1661073783);

		update.setMessage(message);

		return update;
	}

	private Update getUpdateWithCallback(String buttonData) {
		Update update = new Update();

		User user = new User();
		user.setId(201465862L);
		user.setUserName("Dennis_Yudin");
		user.setFirstName("Денис");
		user.setLastName("Юдин");

		Message message = new Message();
		message.setMessageId(731);
		message.setFrom(user);
		message.setDate(1661073783);

		CallbackQuery callbackQuery = new CallbackQuery();
		callbackQuery.setData(buttonData);
		callbackQuery.setFrom(user);
		callbackQuery.setMessage(message);

		update.setCallbackQuery(callbackQuery);

		return update;
	}
}
