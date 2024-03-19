package dev.yudin.yourpersonalfinancesbot.controllers;

import dev.yudin.yourpersonalfinancesbot.exceptions.TelegramBotException;
import dev.yudin.yourpersonalfinancesbot.facades.Facade;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Log4j
@Component
@PropertySource("classpath:application.properties")
public class TelegramBotController extends TelegramLongPollingBot {
	@Value("${telegramBot.userName}")
	private String botName;
	@Value("${telegramBot.botToken}")
	private String botToken;
	private final Facade telegramBotFacade;

	@Autowired
	public TelegramBotController(Facade telegramBotFacade) {
		this.telegramBotFacade = telegramBotFacade;
	}

	@Override
	public void onUpdateReceived(Update update) {

		SendMessage callBack = telegramBotFacade.handle(update);

		try {
			execute(callBack);
		} catch (TelegramApiException e) {
			log.error("Error during handle update from user " + update.getMessage().getFrom().getId());
			throw new TelegramBotException("Error during handle update from user " + update.getMessage().getFrom().getId());
		}
	}

	@Override
	public String getBotUsername() {
		return botName;
	}

	@Override
	public String getBotToken() {
		return botToken;
	}
}
