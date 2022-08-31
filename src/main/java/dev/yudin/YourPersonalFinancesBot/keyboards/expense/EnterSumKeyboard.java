package dev.yudin.YourPersonalFinancesBot.keyboards.expense;

import dev.yudin.YourPersonalFinancesBot.keyboards.Keyboard;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component("EnterSumKeyboard")
public class EnterSumKeyboard implements Keyboard {

	@Override
	public InlineKeyboardMarkup create() {
		InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

		InlineKeyboardButton expenseButton = new InlineKeyboardButton();
		InlineKeyboardButton incomeButton = new InlineKeyboardButton();
		InlineKeyboardButton monthExpenseButton = new InlineKeyboardButton();
		InlineKeyboardButton statsButton = new InlineKeyboardButton();

		expenseButton.setText("100");
		incomeButton.setText("300");
		monthExpenseButton.setText("500");
		statsButton.setText("My sum");

		expenseButton.setCallbackData("100");
		incomeButton.setCallbackData("300");
		monthExpenseButton.setCallbackData("500");
		statsButton.setCallbackData("My sum");

		List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
		keyboardButtonsRow1.add(expenseButton);
		keyboardButtonsRow1.add(incomeButton);

		List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
		keyboardButtonsRow2.add(monthExpenseButton);
		keyboardButtonsRow2.add(statsButton);

		List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
		rowList.add(keyboardButtonsRow1);
		rowList.add(keyboardButtonsRow2);

		inlineKeyboardMarkup.setKeyboard(rowList);

		return inlineKeyboardMarkup;
	}
}
