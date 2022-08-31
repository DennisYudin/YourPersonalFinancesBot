package dev.yudin.YourPersonalFinancesBot.facades;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface Facade {

	SendMessage handle(Update update);
}
