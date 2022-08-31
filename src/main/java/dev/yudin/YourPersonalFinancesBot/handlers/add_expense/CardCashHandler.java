package dev.yudin.YourPersonalFinancesBot.handlers.add_expense;

import dev.yudin.YourPersonalFinancesBot.cache.Cache;
import dev.yudin.YourPersonalFinancesBot.cache.ExpenseCache;
import dev.yudin.YourPersonalFinancesBot.callbacks.Callback;
import dev.yudin.YourPersonalFinancesBot.entities.Expense;
import dev.yudin.YourPersonalFinancesBot.enums.State;
import dev.yudin.YourPersonalFinancesBot.keyboards.Keyboard;
import dev.yudin.YourPersonalFinancesBot.states_handler.Handler;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@Log4j
@Component("CardCashState")
public class CardCashHandler implements Handler {

    public static final String TEXT_MESSAGE = "Choose card or cash";

    private Keyboard keyboard;
    private Callback callback;
    private ExpenseCache expenseCache;
    private Cache cache;

    @Autowired
    public CardCashHandler(@Qualifier("CardOrCashKeyboard") Keyboard keyboard,
                           @Qualifier("CardOrCashCallback") Callback callback,
                           ExpenseCache expenseCache,
                           Cache cache) {
        this.keyboard = keyboard;
        this.callback = callback;
        this.expenseCache = expenseCache;
        this.cache = cache;
    }

    @Override
    public SendMessage apply(Update update) {
        String userId = String.valueOf(update.getCallbackQuery().getFrom().getId());

        InlineKeyboardMarkup buttons = keyboard.create();

        cache.saveState(Long.parseLong(userId), State.EXPENSE_SUM);

        return callback.create(userId, TEXT_MESSAGE, buttons);
    }

    private void saveUserIdDateExpenseName(Update update) {
        long userId = update.getCallbackQuery().getFrom().getId();
        int date = 1660897158;
        java.sql.Date sqlDate = new java.sql.Date(date * 1000L);
        String buttonData = update.getCallbackQuery().getData();

        Expense expense = expenseCache.getExpense(userId);
        expense.setUserId(userId);
        expense.setName(buttonData);
        expense.setDate(sqlDate);
        log.info("expense state: " + expense);
        expenseCache.saveExpense(userId, expense);
    }
}
