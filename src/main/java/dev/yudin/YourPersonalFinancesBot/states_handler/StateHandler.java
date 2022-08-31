package dev.yudin.YourPersonalFinancesBot.states_handler;

import dev.yudin.YourPersonalFinancesBot.enums.State;
import org.springframework.stereotype.Component;

@Component
public class StateHandler {

	public State getState(String data) {
		switch (data) {
			case "Add expense":
				return State.EXPENSE_CATEGORY;
			case "Medical/Healthcare":
			case "Food":
			case "Housing":
			case "Transportation":
			case "Personal":
			case "Utilities":
			case "Clothing":
			case "Education":
			case "Entertainment":
			case "Other":
				return State.CARD_OR_CASH;
			case "Card":
			case "Cash":
				return State.EXPENSE_SUM;
			case "100":
			case "300":
			case "500":
				return State.TRY_AGAIN;
			case "My sum":
				return State.EXPENSE_MY_SUM;
			case "Yes":
				return State.YES_ANSWER;
			case "No":
				return State.NO_ANSWER;
			default:
				return State.MAIN_MENU;
		}
	}
}
