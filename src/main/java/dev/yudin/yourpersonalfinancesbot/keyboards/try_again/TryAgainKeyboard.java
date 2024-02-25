package dev.yudin.yourpersonalfinancesbot.keyboards.try_again;

import dev.yudin.yourpersonalfinancesbot.keyboards.Keyboard;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component("TryAgainKeyboard")
public class TryAgainKeyboard implements Keyboard {

	@Override
	public InlineKeyboardMarkup create() {
		InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

		InlineKeyboardButton expenseButton = new InlineKeyboardButton();
		InlineKeyboardButton incomeButton = new InlineKeyboardButton();

		expenseButton.setText("Yes");
		incomeButton.setText("No");

		expenseButton.setCallbackData("Yes");
		incomeButton.setCallbackData("No");

		List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
		keyboardButtonsRow1.add(expenseButton);
		keyboardButtonsRow1.add(incomeButton);

		List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
		rowList.add(keyboardButtonsRow1);

		inlineKeyboardMarkup.setKeyboard(rowList);

		return inlineKeyboardMarkup;
	}
}
