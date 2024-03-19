package dev.yudin.yourpersonalfinancesbot.handlers.main_menu;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import dev.yudin.yourpersonalfinancesbot.cache.StatesCache;
import dev.yudin.yourpersonalfinancesbot.enums.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

@SpringBootTest
class MainMenuHandlerTest {

	@Autowired
	private MainMenuHandler mainMenuHandler;
	@Autowired
	private StatesCache statesCache;

	@BeforeEach()
	void prepareCache() {
		statesCache.saveState(201465862L, State.MAIN_MENU);
	}

	@Test
	void apply_ShouldReturnMainMenuDialogue_WhenInputIsUpdate() {

		Update update = initUpdate();

		SendMessage callback = mainMenuHandler.handle(update);

		assertTrue(callback.getReplyMarkup().toString().contains("text=Add expense"));
		assertTrue(callback.getReplyMarkup().toString().contains("text=Add income"));
		assertTrue(callback.getReplyMarkup().toString().contains("text=Show expenses"));
		assertTrue(callback.getReplyMarkup().toString().contains("text=Show statistic"));

		State userState = statesCache.defineUserStateBy(201465862L);
		assertEquals(State.MAIN_MENU, userState);
	}

	private Update initUpdate() {
		Update update = new Update();

		User user = new User();
		user.setId(201465862L);
		user.setUserName("Dennis_Yudin");
		user.setFirstName("Денис");
		user.setLastName("Юдин");

		Message message = new Message();
		message.setMessageId(731);
		message.setText("start");
		message.setFrom(user);
		message.setDate(1661073783);

		update.setMessage(message);

		return update;
	}
}
