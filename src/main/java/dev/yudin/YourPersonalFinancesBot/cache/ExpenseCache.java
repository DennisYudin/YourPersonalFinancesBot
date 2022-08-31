package dev.yudin.YourPersonalFinancesBot.cache;

import dev.yudin.YourPersonalFinancesBot.entities.Expense;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ExpenseCache {

	private final Map<Long, Expense> usersStates = new HashMap<>();

	public void saveExpense(long userId, Expense expense) {
		usersStates.put(userId, expense);
	}

	public Expense getExpense(long userId) {
		return usersStates.getOrDefault(userId, new Expense());
	}

	public void cleanCache(long userId) {
		usersStates.remove(userId);
	}
}
