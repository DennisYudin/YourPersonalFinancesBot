package dev.yudin.YourPersonalFinancesBot.keyboards.expense;

import dev.yudin.YourPersonalFinancesBot.keyboards.Keyboard;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component("ChooseExpenseCategoryKeyboard")
public class ChooseExpenseCategoryKeyboard implements Keyboard {

	@Override
	public InlineKeyboardMarkup create() {
		InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

		InlineKeyboardButton healthcareButton = new InlineKeyboardButton();
		InlineKeyboardButton foodButton = new InlineKeyboardButton();
		InlineKeyboardButton housingButton = new InlineKeyboardButton();
		InlineKeyboardButton transportationButton = new InlineKeyboardButton();
		InlineKeyboardButton personalButton = new InlineKeyboardButton();
		InlineKeyboardButton utilitiesButton = new InlineKeyboardButton();
		InlineKeyboardButton clothingButton = new InlineKeyboardButton();
		InlineKeyboardButton educationButton = new InlineKeyboardButton();
		InlineKeyboardButton entertainmentButton = new InlineKeyboardButton();
		InlineKeyboardButton otherButton = new InlineKeyboardButton();

		healthcareButton.setText("Medical/Healthcare");
		foodButton.setText("Food");
		housingButton.setText("Housing");
		transportationButton.setText("Transportation");
		personalButton.setText("Personal");
		utilitiesButton.setText("Utilities");
		clothingButton.setText("Clothing");
		educationButton.setText("Education");
		entertainmentButton.setText("Entertainment");
		otherButton.setText("Other");

		healthcareButton.setCallbackData("Medical/Healthcare");
		foodButton.setCallbackData("Food");
		housingButton.setCallbackData("Housing");
		transportationButton.setCallbackData("Transportation");
		personalButton.setCallbackData("Personal");
		utilitiesButton.setCallbackData("Utilities");
		clothingButton.setCallbackData("Clothing");
		educationButton.setCallbackData("Education");
		entertainmentButton.setCallbackData("Entertainment");
		otherButton.setCallbackData("Other");

		List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
		keyboardButtonsRow1.add(healthcareButton);
		keyboardButtonsRow1.add(foodButton);

		List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
		keyboardButtonsRow2.add(housingButton);
		keyboardButtonsRow2.add(transportationButton);

		List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
		keyboardButtonsRow3.add(personalButton);
		keyboardButtonsRow3.add(utilitiesButton);

		List<InlineKeyboardButton> keyboardButtonsRow4 = new ArrayList<>();
		keyboardButtonsRow4.add(clothingButton);
		keyboardButtonsRow4.add(educationButton);

		List<InlineKeyboardButton> keyboardButtonsRow5 = new ArrayList<>();
		keyboardButtonsRow5.add(entertainmentButton);
		keyboardButtonsRow5.add(otherButton);

		List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
		rowList.add(keyboardButtonsRow1);
		rowList.add(keyboardButtonsRow2);
		rowList.add(keyboardButtonsRow3);
		rowList.add(keyboardButtonsRow4);
		rowList.add(keyboardButtonsRow5);

		inlineKeyboardMarkup.setKeyboard(rowList);

		return inlineKeyboardMarkup;
	}
}
