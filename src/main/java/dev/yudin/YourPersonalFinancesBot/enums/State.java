package dev.yudin.YourPersonalFinancesBot.enums;

import java.util.Arrays;

public enum State {
	MAIN_MENU("Show main menu"),

	ADD_INCOME("Add income"),
	SHOW_EXPENSES("Show expenses"),
	SHOW_STATISTIC("Show statistic"),

	ADD_EXPENSE("Add expense"),
	EXPENSE_CATEGORY("Choose expense category"),
	CARD_OR_CASH("Enter card of cash"),
	EXPENSE_SUM(""),
	EXPENSE_DEFAULT_SUM("Enter sum"),
	EXPENSE_MY_SUM("My sum"),
	EXPENSE_MY_SUM_START(""),
	EXPENSE_MY_SUM_FINISH(""),

	TRY_AGAIN("Try again"),
	YES_ANSWER("Yes"),
	NO_ANSWER("No"),

	UNKNOWN("Unknown");

	private final String stateValue;

	State(String data) {
		this.stateValue = data;
	}

	public String getStateValue() {
		return stateValue;
	}

	public static State fromString(String input) {
		return Arrays.stream(State.values())
				.filter(value -> value.getStateValue().equals(input))
				.findFirst()
				.orElse(UNKNOWN);
	}
}

