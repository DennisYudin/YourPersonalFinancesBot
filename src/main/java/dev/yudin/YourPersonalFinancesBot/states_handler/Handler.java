package dev.yudin.YourPersonalFinancesBot.states_handler;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface Handler {

    SendMessage apply(Update update);
}
