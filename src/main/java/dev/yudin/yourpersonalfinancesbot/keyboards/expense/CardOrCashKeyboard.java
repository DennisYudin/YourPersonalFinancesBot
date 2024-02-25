package dev.yudin.yourpersonalfinancesbot.keyboards.expense;

import dev.yudin.yourpersonalfinancesbot.keyboards.Keyboard;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component("CardOrCashKeyboard")
public class CardOrCashKeyboard implements Keyboard {

	@Override
	public  InlineKeyboardMarkup create() {
		InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

		InlineKeyboardButton expenseButton = new InlineKeyboardButton();
		InlineKeyboardButton incomeButton = new InlineKeyboardButton();

		expenseButton.setText("Card");
		incomeButton.setText("Cash");

		expenseButton.setCallbackData("Card");
		incomeButton.setCallbackData("Cash");

		List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
		keyboardButtonsRow1.add(expenseButton);
		keyboardButtonsRow1.add(incomeButton);

		List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
		rowList.add(keyboardButtonsRow1);

		inlineKeyboardMarkup.setKeyboard(rowList);

		return inlineKeyboardMarkup;
	}
}
