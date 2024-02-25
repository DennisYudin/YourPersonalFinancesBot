package dev.yudin.yourpersonalfinancesbot.handlers.add_expense;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.yudin.yourpersonalfinancesbot.cache.Cache;
import dev.yudin.yourpersonalfinancesbot.enums.State;
import dev.yudin.yourpersonalfinancesbot.states_handler.Handler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

@SpringBootTest
class CardCashHandlerTest {

	@Autowired
	@Qualifier("CardCashState")
	private Handler handler;
	@Autowired
	private Cache statesCache;

	@BeforeEach()
	void prepareCache() {
		statesCache.saveState(201465862L, State.ADD_EXPENSE);
	}

	@Test
	void apply() {

		Update update = getUpdateWithCallback("Food");

		SendMessage callback = handler.apply(update);

		assertTrue(callback.getReplyMarkup().toString().contains("text=Card"));
		assertTrue(callback.getReplyMarkup().toString().contains("text=Cash"));

		State currentState = statesCache.defineStateBy(201465862L);
		assertEquals(State.EXPENSE_SUM, currentState);
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

		update.setMessage(message);
		update.setCallbackQuery(callbackQuery);

		return update;
	}
}