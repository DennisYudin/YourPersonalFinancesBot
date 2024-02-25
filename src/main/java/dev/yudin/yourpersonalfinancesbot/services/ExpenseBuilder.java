package dev.yudin.yourpersonalfinancesbot.services;

import dev.yudin.yourpersonalfinancesbot.cache.ExpenseCache;
import dev.yudin.yourpersonalfinancesbot.dao.impl.ExpenseDAOImpl;
import dev.yudin.yourpersonalfinancesbot.enums.State;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Log4j
@Component
public class ExpenseBuilder {

	private ExpenseCache expenseCache;
	private ExpenseDAOImpl expenseDAO;

	@Autowired
	public ExpenseBuilder(ExpenseCache expenseCache,
						  ExpenseDAOImpl expenseDAO) {
		this.expenseCache = expenseCache;
		this.expenseDAO = expenseDAO;
	}

	public void factory(State state, Update update) {

		if (state == State.EXPENSE_CATEGORY) {
			saveUserIdDateExpenseName(update);
		}
		if (state == State.CARD_OR_CASH) {
			saveCardOrCashInfo(update);
		}
		if (state == State.EXPENSE_DEFAULT_SUM) {
			String sum = update.getCallbackQuery().getData();

//			saveExpenseSum(sum, userId);
		}
	}

	private void saveUserIdDateExpenseName(Update update) {
		long userId = update.getCallbackQuery().getFrom().getId();
		int date = 1660897158;
		java.sql.Date sqlDate = new java.sql.Date(date * 1000L);
		String buttonData = update.getCallbackQuery().getData();

		var expense = expenseCache.getExpense(userId);
		expense.setUserId(userId);
		expense.setExpenseName(buttonData);
//		expense.setDate(sqlDate);
		log.info("expense state: " + expense);
		expenseCache.saveExpense(userId, expense);
	}

	private void saveCardOrCashInfo(Update update) {
		long userId = update.getCallbackQuery().getFrom().getId();
		String buttonData = update.getCallbackQuery().getData();

		var expense = expenseCache.getExpense(userId);
		expense.setExpenseType(buttonData);
		log.info("expense current state: " + expense);
		expenseCache.saveExpense(userId, expense);
	}

	private void saveExpenseSum(String sum, long userId) {
		var expense = expenseCache.getExpense(userId);
		expense.setSum(Integer.parseInt(sum));

		log.info("expense state: " + expense);
		expenseCache.saveExpense(userId, expense);
		//Save into database STUB
		expenseDAO.save(expense);

		expenseCache.cleanCache(userId);
		log.info("ExpenseCache: " + expenseCache.getExpense(userId));
	}
}
