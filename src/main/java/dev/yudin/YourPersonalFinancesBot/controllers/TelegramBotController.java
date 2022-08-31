package dev.yudin.YourPersonalFinancesBot.controllers;

import dev.yudin.YourPersonalFinancesBot.facades.Facade;
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

	private Facade facade;

	@Autowired
	public TelegramBotController(Facade facade) {
		this.facade = facade;
	}

	@Override
	public void onUpdateReceived(Update update) {
		SendMessage callBack = facade.handle(update);
		try {
			execute(callBack);
		} catch (TelegramApiException e) {
			log.error("Error during execute()");
			e.printStackTrace();
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
