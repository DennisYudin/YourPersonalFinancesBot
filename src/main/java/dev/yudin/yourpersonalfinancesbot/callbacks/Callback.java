package dev.yudin.yourpersonalfinancesbot.callbacks;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public interface Callback {

	SendMessage create(String userId,
					   String textMessage,
					   InlineKeyboardMarkup keyboard);
}
