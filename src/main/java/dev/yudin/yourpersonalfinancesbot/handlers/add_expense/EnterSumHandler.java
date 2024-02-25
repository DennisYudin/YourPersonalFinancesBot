package dev.yudin.yourpersonalfinancesbot.handlers.add_expense;

import dev.yudin.yourpersonalfinancesbot.cache.Cache;
import dev.yudin.yourpersonalfinancesbot.cache.ExpenseCache;
import dev.yudin.yourpersonalfinancesbot.callbacks.Callback;
import dev.yudin.yourpersonalfinancesbot.enums.State;
import dev.yudin.yourpersonalfinancesbot.keyboards.Keyboard;
import dev.yudin.yourpersonalfinancesbot.states_handler.Handler;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@Log4j
@Component("EnterSumState")
public class EnterSumHandler implements Handler {

    public static final String TEXT_MESSAGE = "Choose or enter sum";

    private Keyboard keyboard;
    private Callback callback;
    private ExpenseCache expenseCache;
    private Cache statesCache;

    @Autowired
    public EnterSumHandler(@Qualifier("EnterSumKeyboard") Keyboard keyboard,
                           @Qualifier("EnterSumCallback") Callback callback,
                           ExpenseCache expenseCache,
                           Cache statesCache) {
        this.keyboard = keyboard;
        this.callback = callback;
        this.expenseCache = expenseCache;
        this.statesCache = statesCache;
    }

    @Override
    public SendMessage apply(Update update) {
        String userId = String.valueOf(update.getCallbackQuery().getFrom().getId());

//        saveCardOrCashInfo(update);

        InlineKeyboardMarkup keyboardWithButtons = keyboard.create();

        statesCache.saveState(Long.parseLong(userId), State.EXPENSE_SUM);

        return callback.create(userId, TEXT_MESSAGE, keyboardWithButtons);
    }

    private void saveCardOrCashInfo(Update update) {
        long userId = update.getCallbackQuery().getFrom().getId();
        String buttonData = update.getCallbackQuery().getData();

        var expense = expenseCache.getExpense(userId);
        expense.setExpenseType(buttonData);
        log.info("expense current state: " + expense);
        expenseCache.saveExpense(userId, expense);
    }
}
