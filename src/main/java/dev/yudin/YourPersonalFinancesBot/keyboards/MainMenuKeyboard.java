package dev.yudin.YourPersonalFinancesBot.keyboards;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component("MainMenuKeyboard")
public class MainMenuKeyboard implements Keyboard {

	@Override
	public InlineKeyboardMarkup create() {

		InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

		InlineKeyboardButton expenseButton = new InlineKeyboardButton();
		InlineKeyboardButton incomeButton = new InlineKeyboardButton();
		InlineKeyboardButton monthExpenseButton = new InlineKeyboardButton();
		InlineKeyboardButton statsButton = new InlineKeyboardButton();

		expenseButton.setText("Add expense");
		incomeButton.setText("Add income");
		monthExpenseButton.setText("Show expenses");
		statsButton.setText("Show statistic");

		expenseButton.setCallbackData("Add expense");
		incomeButton.setCallbackData("Add income");
		monthExpenseButton.setCallbackData("Show expenses");
		statsButton.setCallbackData("Show statistic");

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
